package com.nutripet.entrega.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutaDespachoResponseDTO {

    private Long idRuta;
    private Long zonaId;
    
    private String zonaNombre; 
    
    private LocalDate fechaRuta;
    private Long usuarioId;
    private String estado;
}