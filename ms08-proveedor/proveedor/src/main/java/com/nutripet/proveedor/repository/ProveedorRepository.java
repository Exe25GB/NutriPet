package com.nutripet.proveedor.repository;

import com.nutripet.proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByNombreEmpresaContainingIgnoreCase(String nombre);
    
}
