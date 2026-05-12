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
    private String datosProveedor;
    private String condicionesProveedor;

    private String estadoOrden;
    private String nombreMarca;

}
