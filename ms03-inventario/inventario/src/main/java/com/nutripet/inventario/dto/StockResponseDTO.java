package com.nutripet.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {

    private Long idStock;
    private Long idProducto;
    private Integer cantidadActual;
    private Integer stockMinimo;
    private LocalDate fechaVencimiento;
    private String numeroLote;
    private String ubicacionBodega;
}
