package com.nutripet.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//La función de @Entity te ayuda a señalarle a la arquitectura SpringBoot que esta clase representa una tabla en concreto.
@Entity
// @Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "movimientos_stock")
//Implementa el constructor Getter, Setter.
@Data
@NoArgsConstructor
//Genera un constructor que recibe un argumento por cada atributo de la clase.
@AllArgsConstructor
public class MovimientoStock {

    //@Id identifica este atributo como una clave primaria (Primary Key).
    @Id
    //Genera automaticamente su valor sumandole un numero mas +1 = 2 la siguiente id +1 = 3. 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;

    //'Nullable = false' : No se puede guardar un movimiento en el sistema sin asociarlo obligatoriamente a un producto.
    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    //'Nullable = false' : El tipo de movimiento (ej. ENTRADA, SALIDA) es obligatorio para la trazabilidad.
    //'Length = 20' : Cantidad límite de caracteres de texto que puede tener al ingresar el valor a la tabla.
    @Column(name = "tipo_movimiento", length = 20, nullable = false)
    private String tipo;
    
    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;

    @Column(name = "motivo", length = 255)
    private String motivo; 
}