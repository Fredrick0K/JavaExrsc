package com.magnum.facturacion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.magnum.facturacion.model.Producto;
import com.magnum.facturacion.model.DTO.ProductoDTO;
import com.magnum.facturacion.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository prodRepo;
    private final ModelMapper mapper;

    public ProductoService(ProductoRepository proRepo, ModelMapper mapper) {
        this.prodRepo = proRepo;
        this.mapper = mapper;
    }

    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto = mapper.map(productoDTO, Producto.class);
        return mapper.map(prodRepo.save(producto), ProductoDTO.class);
    }

    public List<ProductoDTO> findAll() {
        return prodRepo.findAll().stream().map(
                prod -> {
                    return mapper.map(prod, ProductoDTO.class);
                }).collect(Collectors.toList());
    }

    public Optional<ProductoDTO> findById(int id) {
        return prodRepo.findById(id).map(
                prod -> {
                    return mapper.map(prod, ProductoDTO.class);
                });
    }

    public boolean deleteById(int id) {
        return prodRepo.findById(id).map(prod -> {
            prodRepo.delete(prod);
            return true;
        }).orElse(false);
    }

    public Optional<ProductoDTO> update(ProductoDTO productoDTO) {

        Producto producto = mapper.map(productoDTO, Producto.class);

        return prodRepo.findById(producto.getId()).map(
                prod -> {
                    return mapper.map(prodRepo.save(producto), ProductoDTO.class);
                });
    }
}
