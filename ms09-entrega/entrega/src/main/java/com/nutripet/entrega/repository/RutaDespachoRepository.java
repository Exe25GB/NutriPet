package com.nutripet.entrega.repository;

import com.nutripet.entrega.model.RutaDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RutaDespachoRepository extends JpaRepository<RutaDespacho, Long> {

    List<RutaDespacho> findByFechaRuta(LocalDate fechaRuta);

    List<RutaDespacho> findByUsuarioId(Long usuarioId);

    List<RutaDespacho> findByZonaId(Long zonaId);

    List<RutaDespacho> findByEstado(String estado);
    
}