package com.nutripet.entrega.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregaRequestDTO {

    @NotNull(message = "El id del usuario debe de ser obligatorio.")
    @Positive(message = "El id debe ser mayor a cero.")
    private Long idUsuario;

    @NotNull(message = "El id de la ruta debe de ser obligatorio.")
    @Positive(message = "El id de ruta debe ser positivo.")
    private Long idRuta;


}
