package com.nutripet.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// DTO DE SALIDA
public class DireccionResponseDTO {
    // Estructura al moverse por el service
    private Long idDireccion;
    private Long idCliente;
    private String calle;
    private String numero;
    private String comuna;
    private String region;

}
