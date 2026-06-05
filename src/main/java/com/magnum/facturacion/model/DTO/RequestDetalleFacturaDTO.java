package com.magnum.facturacion.model.DTO;

import lombok.Data;

@Data
public class RequestDetalleFacturaDTO {

    private int idProducto;
    private int cantidad;
}
