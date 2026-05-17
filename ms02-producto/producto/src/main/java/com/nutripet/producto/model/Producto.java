package com.nutripet.producto.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//La función de @Entity te ayuda a señalarle a la arquitectura SpingBoot que esta clase representa una tabla en concreto. 
@Entity
//@Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "productos")
// Implementa el constructor Getter y Setter.
@Data
@NoArgsConstructor
// Genera un constructor que recibe un argumento por cada atributo de la clase
@AllArgsConstructor
public class Producto {

    //@Id identifica este atributo como una clave primaria.
    @Id
    //Genera automaticamente su valor sumandole un numero mas +1 = 2 la siguiente id +1 = 3
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;

    @Column(nullable = false)
    private String skuProducto;

    @Column(nullable = false, length = 255)
    private String nombre;
    //'Precision = 8' : Exige que la cantidad limite de catacteres debe ser ocho.
    //'Scale = 2' :Exige que la cantidad limite de **Decimales** debe ser dos.
    @Column(precision = 8, scale = 2)
    private BigDecimal peso;

    //'columnDefinition = "TEXT" ' : Transforma el tipo de dato del atributo a TEXT
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @JoinColumn(nullable = false)
    private long idCategoria;

    @Column(name = "ciclo_vital", length = 50)
    private String cicloVital; 

    @Column(length = 100)
    private String marca;

}
