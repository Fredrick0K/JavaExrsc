package com.magnum.facturacion.model.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoDTO {

    private int id;
    private String nombre;
    private String detalle;
    private BigDecimal precio;

}
