package com.nutripet.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioReponseDTO {

    private Long idInventario;
    private Long idProducto;
    private Integer stockTotalInventario;


    private String tipoMovimiento;
    private Integer cantidadLote;



}
