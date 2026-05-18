package com.nutripet.precio.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@mapea la clase para poder estructurar en la base de datos
//@nombre de la tabla
@Entity
@Table(name = "Precio")
//funciones @gets y setter @contructores llenos @contructores vacios
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrecio;

    //>id_producto< es una clave forania de la Clase producto del ms02-producto
    @Column(nullable = false, unique = true)
    private Long idProducto;

    @Column(nullable = false)
    private BigDecimal precioBase;

}
