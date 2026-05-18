package com.nutripet.precio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.precio.model.Descuento;
import com.nutripet.precio.model.TipoCliente;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    
    List<Descuento> findByTipoCliente(TipoCliente tipoCliente);

}
