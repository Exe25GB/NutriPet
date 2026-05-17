package com.nutripet.entrega.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZonaRequestDTO {

    @NotBlank(message = "El nombre de la zona es obligatorio")
    @Size(max = 100, message = "El nombre de la zona no puede superar los 100 caracteres")
    private String nombre;
}