package com.nutripet.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.pago.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

}
