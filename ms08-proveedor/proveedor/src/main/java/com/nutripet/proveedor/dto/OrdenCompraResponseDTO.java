package com.nutripet.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraResponseDTO {

    private Long idOrden;
    private Long proveedorId;
    
    private String nombreEmpresa; 
    
    private String estado;
}
