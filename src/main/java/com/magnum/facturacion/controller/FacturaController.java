package com.magnum.facturacion.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.magnum.facturacion.model.DTO.RequestFacturaDTO;
import com.magnum.facturacion.model.DTO.ResponseFacturaDTO;
import com.magnum.facturacion.service.FacturaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/facturas")
@Slf4j
public class FacturaController {

    private final FacturaService facService;

    public FacturaController(FacturaService facService) {
        this.facService = facService;
    }

    @PostMapping
    public ResponseFacturaDTO save(@RequestBody RequestFacturaDTO facturaDTO) {
        
        return facService.save(facturaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseFacturaDTO>> findAll() {
        List<ResponseFacturaDTO> responseFacturaDTO = facService.findAll();
        if (responseFacturaDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responseFacturaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFacturaDTO> findById(@PathVariable int id) {

        Optional<ResponseFacturaDTO> factura = facService.findById(id);
        return factura.map(
                ResponseEntity::ok).orElseGet(
                        () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        facService.deleteById(id);
    }
}
