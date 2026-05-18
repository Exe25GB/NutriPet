package com.nutripet.producto.model;

import java.math.BigDecimal;

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
    private Long idProducto;
    
    //'Unique = True' : Identifica que cada valor agregado a la base de datos SI O SI debe ser único.
    //'Nullable = false' : No se puede guardar un producto en el sistema si el campo del SKU viene vacío o nulo.
    //'Length = 50' : Cantidad de caracteres que puede tener al ingresar el valor a la tabla.
    @Column(unique = true, nullable = false, length = 50)
    private String sku; 

    @Column(nullable = false, length = 255)
    private String nombre;
    //'Precision = 8' : Exige que la cantidad limite de catacteres debe ser ocho.
    //'Scale = 2' :Exige que la cantidad limite de **Decimales** debe ser dos.
    @Column(precision = 8, scale = 2)
    private BigDecimal peso;

    //'columnDefinition = "TEXT" ' : Transforma el tipo de dato del atributo a TEXT
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    //>idCategoria< es una clave foranea de la clase categoria

    //@ManyToOne : "Muchas filas de la tabla de productos pueden apuntar a una sola fila en la tabla de categorías."
    @ManyToOne
    //@JoinColumn : Esta es la función de une a la tabla de categoria 
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "tipo_mascota", length = 50)
    private String tipoMascota; 

    @Column(name = "ciclo_vital", length = 50)
    private String cicloVital; 

    @Column(length = 100)
    private String marca;

}
