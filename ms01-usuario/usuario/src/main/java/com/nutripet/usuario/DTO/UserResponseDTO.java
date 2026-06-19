package com.nutripet.usuario.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private String direccion;
    private String tipoUsuarioNombre;
}
