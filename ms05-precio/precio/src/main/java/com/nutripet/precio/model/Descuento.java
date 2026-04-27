package com.nutripet.precio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "descuento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idDescuento;

    //>id_producto< es una clave forania de la Clase producto del ms02-producto

    private TipoCliente tipoCliente;

    private BigDecimal porcentajeDescuento;

    private BigDecimal compraMinima;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFinal;


}
