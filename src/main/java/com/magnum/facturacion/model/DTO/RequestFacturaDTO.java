package com.magnum.facturacion.model.DTO;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFacturaDTO {

    private int id;
    private String numeroFactura;
    private Set<RequestDetalleFacturaDTO> detalleFactura;

}
