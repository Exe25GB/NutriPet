package com.nutripet.entrega.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregaReponseDTO {
    
    private Long idAsignacion;
    private Long idUsuario;
    private Long idRuta;

    private String nombreZona;
    private LocalDateTime fechaRuta;

}
