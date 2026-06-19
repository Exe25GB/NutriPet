package com.nutripet.notificacion.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinatario; // Email o número de cel

    @Column(length = 4000, nullable = false)
    private String mensaje;

    private LocalDateTime fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "tipo_notificacion_id", nullable = false)
    private TipoNotificacion tipoNotificacion;
}
