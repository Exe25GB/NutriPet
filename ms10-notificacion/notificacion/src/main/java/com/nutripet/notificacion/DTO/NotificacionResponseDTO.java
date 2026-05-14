package com.nutripet.notificacion.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionResponseDTO {

    private Long id;
    private String destinatario;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private String tipoNombre;

}
