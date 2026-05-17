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

//La función de @Entity te ayuda a señalarle a la arquitectura SpringBoot que esta clase representa una tabla en concreto.
@Entity
// @Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "ordenes_compra")
//Genera automáticamente Getters y Setters.
@Data
@NoArgsConstructor
//Genera un constructor que recibe un argumento por cada atributo de la clase.
@AllArgsConstructor
public class OrdenCompra {

    //@Id identifica este atributo como una clave primaria (Primary Key).
    @Id
    //Genera automáticamente su valor delegando a la base de datos el auto-incremento numérico (+1).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    //'Nullable = false' : No se puede guardar una orden en el sistema sin asociarla obligatoriamente al ID de un proveedor.
    @Column(name = "proveedor_id", nullable = false)
    private Long proveedorId;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;
}