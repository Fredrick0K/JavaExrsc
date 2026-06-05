package com.magnum.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magnum.facturacion.model.DetalleFactura;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer>{

}
