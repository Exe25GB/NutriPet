package com.nutripet.cliente.dto;

import com.nutripet.cliente.model.TipoCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// DTO DE ENTRADA
public class ClienteRequestDTO {
    // Estructura al moverse al entrar a la BD
 // @NotNull: NO VACIO - validaciones de seguridad para evitar datos incorrecto hacia la bd
    @NotNull(message = "El id del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El tipo de cliente es obligatorio 'MAYORISTA' o 'MINORISTA'")
    private TipoCliente tipoCliente;

    private String razonSocial;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

}
