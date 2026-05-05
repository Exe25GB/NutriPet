package com.nutripet.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 320)
    private String correoUsuario;

    @Column(nullable = false, length = 127)
    private String contraseñaUsuario;

    @Column(nullable = false, length = 50)
    private String primerNombre;

    @Column(nullable = true, length = 50)
    private String segundoNombre;

    @Column(nullable = false, length = 50)
    private String primerApellido;

    @Column(nullable = false, length = 50)
    private String segundoApellido;

}
