package com.magnum.facturacion.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseFacturaDTO {

    private int id;
    private String numeroFactura;
    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDateTime fechaCreado;
    private Set<ResponseDetalleFacturaDTO> detalleFacturas;
}
