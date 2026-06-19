package com.nutripet.entrega.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutaDespachoRequestDTO {

    @NotNull(message = "El ID de la zona logística es obligatorio")
    private Long zonaId;

    @NotNull(message = "La fecha programada para la ruta es obligatoria")
    private LocalDate fechaRuta;

    @NotNull(message = "El ID del repartidor asignado es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El estado de la ruta no puede estar vacío")
    private String estado;
}