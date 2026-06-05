package com.magnum.facturacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.magnum.facturacion.model.DTO.ProductoDTO;
import com.magnum.facturacion.service.ProductoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/productos")
@Slf4j
public class ProductoController {

    private final ProductoService prodService;

    public ProductoController(ProductoService prodService) {
        this.prodService = prodService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@RequestBody ProductoDTO productoDTO) {
        log.info("Guardado!");
        return new ResponseEntity<>(prodService.save(productoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {
        List<ProductoDTO> productoDTOs = prodService.findAll();

        if (productoDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productoDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable int id) {
        return prodService.findById(id).map(
                prod -> {
                    return ResponseEntity.ok(prod);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        if (prodService.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> update(@RequestBody ProductoDTO productoDTO) {
        return prodService.update(productoDTO).map(
                ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
