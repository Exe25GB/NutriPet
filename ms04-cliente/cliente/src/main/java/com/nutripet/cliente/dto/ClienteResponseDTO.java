package com.nutripet.cliente.dto;

import com.nutripet.cliente.model.TipoCliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// DTO DE SALIDA
public class ClienteResponseDTO {
    // Estructura al moverse por el service
    private Long idCliente;
    private Long idUsuario;
    private TipoCliente tipoCliente;
    private String razonSocial;
    private String telefono;

}
