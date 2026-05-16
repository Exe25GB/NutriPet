package com.nutripet.proveedor.model;

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
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(name = "nombre_empresa", length = 255, nullable = false)
    private String nombreEmpresa;

    @Column(name = "datos_contacto", columnDefinition = "TEXT")
    private String datosContacto;

    @Column(name = "condiciones", columnDefinition = "TEXT")
    private String condiciones;

    @Column(name = "marcas_ofrecidas", columnDefinition = "TEXT")
    private String marcasOfrecidas;
}