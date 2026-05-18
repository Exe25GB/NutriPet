package com.nutripet.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.cliente.model.DireccionEnvio;

                                               // JpaRepository: METODOS - se hereda todos los metodos basicos de bd (save, findById, findAll, delete)
public interface DireccionEnvioRepository extends JpaRepository<DireccionEnvio, Long>{

}
