package com.nutripet.proveedor.model;

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
@Table(name = "proveedores")
// Genera automáticamente Getters y Setters.
@Data
// Genera un constructor vacío (sin parámetros), el cual es obligatorio para que JPA e Hibernate puedan estructurar los datos.
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    // @Id identifica este atributo como una clave primaria (Primary Key).
    @Id
    // Genera automáticamente su valor delegando a la base de datos el auto-incremento numérico (+1).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    // 'Nullable = false' : No se puede registrar un proveedor si el campo del nombre de la empresa viene vacío o nulo.
    // 'Length = 255' : Cantidad límite de caracteres de texto para el nombre.
    @Column(name = "nombre_empresa", length = 255, nullable = false)
    private String nombreEmpresa;

    // ' columnDefinition = "TEXT" ' : Transforma el tipo de dato del atributo a TEXT, ideal para guardar múltiples correos, teléfonos y direcciones sin límite corto.
    @Column(name = "datos_contacto", columnDefinition = "TEXT")
    private String datosContacto;

    @Column(name = "condiciones", columnDefinition = "TEXT")
    private String condiciones;

    @Column(name = "marcas_ofrecidas", columnDefinition = "TEXT")
    private String marcasOfrecidas;
}