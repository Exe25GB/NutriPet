package com.nutripet.pago.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metodo_pagos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMetodo;

    @Column(name = "nombre_plataforma", nullable = false, length = 50)
    private String nombrePlataforma;

    @Column(name = "codigo_comercio", nullable = false, length = 100)
    private String codigoComercio;

}
