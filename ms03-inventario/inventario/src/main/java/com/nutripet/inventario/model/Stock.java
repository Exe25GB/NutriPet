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

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @Column(nullable = false)
    private Long idProducto;

    @Column(nullable = false)
    private Integer cantidadActual;

    @Column(nullable = false)
    private Integer stockMinimo;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(length = 50)
    private String numeroLote;

    @Column(length = 100)
    private String ubicacionBodega; 
}
