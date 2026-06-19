package com.nutripet.notificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutripet.notificacion.model.Notificacion;

public interface NotificacionRepository  extends JpaRepository<Notificacion, Long>{

    List<Notificacion> findByDestinatarioContainingIgnoreCase(String destinatario);

    List<Notificacion> findByFechaEnvioIsNull();

    @Query("SELECT n FROM Notificacion n WHERE n.tipoNotificacion.id = :tipoId")
    List<Notificacion> findByTipoNotificacionId(@Param("tipoId") Long tipoId);

    @Query("SELECT n FROM Notificacion n WHERE n.mensaje LIKE %:texto% ORDER BY n.fechaEnvio DESC")
    List<Notificacion> buscarPorTexto(@Param("texto") String texto);

    @Query(
        value = "SELECT * FROM notificaciones WHERE destinatario = :destino AND fecha_envio >= DATE_SUB(NOW(), INTERVAL 1 DAY)",
        nativeQuery = true
    )
    List<Notificacion> buscarRecientesPorDestino(@Param("destino") String destino);

}
