package com.nutripet.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// DTO DE ENTRADA
public class DireccionRequestDTO {
    // Estructura al moverse al entrar a la BD
 // @NotNull: NO VACIO - validaciones de seguridad para evitar datos incorrecto hacia la bd
    @NotBlank(message = "La calle es obligatoria")
    private String calle;

    @NotBlank(message = "El número es obligatorio")
    private String numero;

    @NotBlank(message = "La comuna es obligatoria")
    private String comuna;

    @NotBlank(message = "La región es obligatoria")
    private String region;

}
