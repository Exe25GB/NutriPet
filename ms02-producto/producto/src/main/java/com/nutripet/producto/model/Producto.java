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

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    private Long skuProducto;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = false)
    private BigDecimal pesoProducto;

    //Descripción de producto.
    @Column(nullable = false)
    private String descProducto;
    
    //Clave foránea
    @JoinColumn(nullable = false)
    private long idCategoria;



    


}
