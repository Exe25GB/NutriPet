package com.nutripet.precio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.precio.model.Precio;

public interface PrecioRepository extends JpaRepository<Precio, Long>{
    List<Precio> findByIdProducto(Long idProducto);

}
