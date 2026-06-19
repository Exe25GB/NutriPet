package com.nutripet.proveedor.repository;

import com.nutripet.proveedor.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    List<OrdenCompra> findByProveedorId(Long proveedorId);

    List<OrdenCompra> findByEstado(String estado);
    
}
