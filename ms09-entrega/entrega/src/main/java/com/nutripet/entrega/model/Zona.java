package com.nutripet.entrega.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// La función de @Entity te ayuda a señalarle a la arquitectura SpringBoot que esta clase representa una tabla en concreto.
@Entity
// @Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "zonas")
// Genera automáticamente Getters y Setters.
@Data
@NoArgsConstructor
// Genera un constructor que recibe un argumento por cada atributo de la clase.
@AllArgsConstructor
public class Zona {

    // @Id identifica este atributo como una clave primaria (Primary Key).
    @Id
    // Genera automáticamente su valor delegando a la base de datos el auto-incremento numérico (+1).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idZona;

    // 'Nullable = false' : No se puede registrar una zona si el campo del nombre (ej. "Quilpué Norte", "Belloto Sur") viene vacío o nulo.
    // 'Length = 100' : Cantidad límite de caracteres de texto para el nombre de la zona.
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
}
