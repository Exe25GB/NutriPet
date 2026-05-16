package com.nutripet.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponseDTO {

    private Long idProveedor;
    private String nombreEmpresa;
    private String datosContacto;
    private String condiciones;
    private String marcasOfrecidas;
}