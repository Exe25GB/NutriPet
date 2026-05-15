package com.nutripet.producto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtributoRequestDTO {
    @NotBlank(message = "El tipo de mascota es obligatorio")
    private String tpMascotaAtributo;
    @NotBlank(message = "El ciclo vital es obligatorio")
    private String cVitalAtributo;
    @NotBlank(message = "La marca es obligatoria")
    private String marcaAtributo;
}