package com.nutripet.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private String tipoCliente;
    private String razonSocial;
    private String direccionDespacho;

}
