package com.nutripet.producto.model;

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
//@Table localiza la estructura de la tabla en la base de datos del micro-servicio + le das el nombre que le quieres dar a la tabla.
@Table(name = "categoria_productos")
//Implementa el constructor Getter y Setter forma automática.
@Data
@NoArgsConstructor
//Genera un constructor que recibe un argumento por cada atributo de la clase.
@AllArgsConstructor
public class Categoria {

    //@Id identifica este atributo como una clave primaria.
    @Id
    //Genera automaticamente su valor sumandole un numero mas +1 = 2 la siguiente id +1 = 3. 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    // 'Nullable = false' : No se puede guardar una categoría en el sistema si el campo del nombre viene vacío o nulo.
    // 'Length = 100' : Cantidad límite de caracteres de texto que puede tener el nombre al ingresar el valor a la tabla.
    @Column(nullable = false, length = 100)
    private String nombre;

}
