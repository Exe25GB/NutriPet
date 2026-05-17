package com.nutripet.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// La función de @Entity te ayuda a señalarle a la arquitectura SpringBoot que esta clase representa una tabla en concreto.
@Entity
// @Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "stock")
// Implementa el constructor Getter y Setter.
@Data
@NoArgsConstructor
// Genera un constructor que recibe un argumento por cada atributo de la clase.
@AllArgsConstructor
public class Stock {

    // @Id identifica este atributo como una clave primaria (Primary Key).
    @Id
    // Genera automáticamente su valor delegando a la base de datos el auto-incremento numérico (+1).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    // 'Nullable = false' : Es obligatorio vincular este registro al identificador del producto maestro del MS-02.
    @Column(nullable = false)
    private Long idProducto;

    @Column(nullable = false)
    private Integer cantidadActual;

    @Column(nullable = false)
    private Integer stockMinimo;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    // 'Length = 50' : Cantidad límite de caracteres de texto que puede tener el código de lote del proveedor.
    @Column(length = 50)
    private String numeroLote;

    @Column(length = 100)
    private String ubicacionBodega; 
}
