package com.nutripet.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Email(message = "El correo es invalido")
    @NotBlank(message = "El correo no puede estar vacio")
    @Size(max = 320, message = "El correo no puede exceder los 320 caracteres")
    @Column(name = "correo_usuario", nullable = false, length = 320)
    private String correoUsuario;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(max = 127, min = 12, message = "La contraseña debe de tener minimo 12 caracteres y maximo 127")
    @Column(name = "contraseña_usuario", nullable = false, length = 127)
    private String contraseñaUsuario;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 50, message = "El maximo de caracteres es de 50")
    @Column(name = "primer_nombre", nullable = false, length = 50)
    private String primerNombre;

    @Size(max = 50, message = "El maximo de caracteres es de 50")
    @Column(name = "segundo_nombre", nullable = true, length = 50)
    private String segundoNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 50, message = "El maximo de caracteres es de 50")
    @Column(name = "primer_apellido", nullable = false, length = 50)
    private String primerApellido;

    @NotBlank(message = "El segundo apellido es obligatorio")
    @Size(max = 50, message = "El maximo de caracteres es de 50")
    @Column(name = "segundo_apellido", nullable = false, length = 50)
    private String segundoApellido;

}
