package com.magnum.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magnum.facturacion.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
