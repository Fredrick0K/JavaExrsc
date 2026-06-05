package com.magnum.facturacion.model.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ResponseDetalleFacturaDTO {

    private int idProducto;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal total;
}
