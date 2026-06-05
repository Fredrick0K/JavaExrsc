package com.magnum.facturacion.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.magnum.facturacion.model.DetalleFactura;
import com.magnum.facturacion.model.Factura;
import com.magnum.facturacion.model.Producto;
import com.magnum.facturacion.model.DTO.RequestDetalleFacturaDTO;
import com.magnum.facturacion.model.DTO.RequestFacturaDTO;
import com.magnum.facturacion.model.DTO.ResponseFacturaDTO;
import com.magnum.facturacion.repository.FacturaRepository;
import com.magnum.facturacion.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaService {

    private final FacturaRepository facRepo;
    private final ProductoRepository prodRepo;
    private final ModelMapper mapper;

    public FacturaService(FacturaRepository facRepo, ProductoRepository prodRepo, ModelMapper mapper) {
        this.facRepo = facRepo;
        this.prodRepo = prodRepo;
        this.mapper = mapper;
    }

    public List<ResponseFacturaDTO> findAll() {
        return facRepo.findAll().stream().map(
                factura -> mapper.map(factura, ResponseFacturaDTO.class)).collect(Collectors.toList());
    }

    public Optional<ResponseFacturaDTO> findById(int id) {
        return facRepo.findById(id).map(
                factura -> mapper.map(factura, ResponseFacturaDTO.class));
    }

    public void deleteById(int id) {
        facRepo.deleteById(id);
    }

    @Transactional
    public ResponseFacturaDTO save(RequestFacturaDTO facturaDTO) {

        Factura factura = new Factura();
        BigDecimal subtotal = BigDecimal.ZERO;
        Set<DetalleFactura> detalle = new HashSet<>();

        factura.setNumeroFactura(facturaDTO.getNumeroFactura());

        for (RequestDetalleFacturaDTO detalleFacturaDTO : facturaDTO.getDetalleFactura()) {

            Producto producto = prodRepo.findById(detalleFacturaDTO.getIdProducto()).orElseThrow(
                () -> new RuntimeException("Producto No Encontrado.")
            );
            BigDecimal totalProducto = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(detalleFacturaDTO.getCantidad()));
            subtotal = subtotal.add(totalProducto);

            DetalleFactura detalleFactura = new DetalleFactura();

            detalleFactura.setIdProducto(detalleFacturaDTO.getIdProducto());
            detalleFactura.setPrecio(producto.getPrecio());
            detalleFactura.setCantidad(detalleFacturaDTO.getCantidad());
            detalleFactura.setTotal(totalProducto);
            detalleFactura.setFactura(factura);
            detalle.add(detalleFactura);
        }
        
        factura.setDetalleFacturas(detalle);
        factura.setSubtotal(subtotal);
        factura.setTotal(subtotal.add(subtotal.multiply(BigDecimal.valueOf(factura.getIVA()))));

        Factura savedFactura = facRepo.save(factura);

        return mapper.map(savedFactura, ResponseFacturaDTO.class);
    }
}
