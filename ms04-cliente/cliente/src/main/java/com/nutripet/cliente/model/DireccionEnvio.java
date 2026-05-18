package com.nutripet.cliente.model;

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

@Entity
@Table(name = "direccion_envios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionEnvio {

 // @Id:
    @Id
 // @GeneratedValue:
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    // Relación Muchos-a-Uno: Muchas direcciones pertenecen a un mismo cliente.
    // @JoinColumn: Define el nombre de la columna en la tabla SQL que actuará como llave foránea.
 // @ManyToOne:
    @ManyToOne
 // @JoinColumn:
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String comuna;

    @Column(nullable = false)
    private String region;


}
