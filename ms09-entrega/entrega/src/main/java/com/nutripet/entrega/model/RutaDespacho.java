package com.nutripet.entrega.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// La función de @Entity te ayuda a señalarle a la arquitectura SpringBoot que esta clase representa una tabla en concreto.
@Entity
// @Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "rutas_despacho")
// Genera automáticamente Getters y Setters.
@Data
@NoArgsConstructor
// Genera un constructor que recibe un argumento por cada atributo de la clase.
@AllArgsConstructor
public class RutaDespacho {

    // @Id identifica este atributo como una clave primaria (Primary Key).
    @Id
    // Genera automáticamente su valor delegando a la base de datos el auto-incremento numérico (+1).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    // 'Nullable = false' : No se puede guardar una ruta en el sistema sin asociarla obligatoriamente al ID de una zona de despacho.
    @Column(name = "zona_id", nullable = false)
    private Long zonaId;

    // 'Nullable = false' : La fecha programada para la ruta es obligatoria para organizar la logística diaria.
    @Column(name = "fecha_ruta", nullable = false)
    private LocalDate fechaRuta;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;
}