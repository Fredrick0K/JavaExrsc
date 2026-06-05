package com.magnum.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magnum.facturacion.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}
