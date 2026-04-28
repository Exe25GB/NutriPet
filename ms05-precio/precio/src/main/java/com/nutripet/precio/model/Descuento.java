package com.nutripet.precio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "descuentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescuento;

    //>id_producto< es una clave forania de la Clase producto del ms02-producto
    @JoinColumn
    private Long idProdutco;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    private BigDecimal porcentajeDescuento;

    @Column(nullable = false)
    private BigDecimal compraMinima;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFinal;


}
