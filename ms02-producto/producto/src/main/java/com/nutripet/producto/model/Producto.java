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
    private long idProducto;

    @Column(nullable = false)
    private String skuProducto;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(precision = 8, scale = 2)
    private BigDecimal peso;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @JoinColumn(nullable = false)
    private long idCategoria;

    @Column(name = "ciclo_vital", length = 50)
    private String cicloVital; 

    @Column(length = 100)
    private String marca;

}
