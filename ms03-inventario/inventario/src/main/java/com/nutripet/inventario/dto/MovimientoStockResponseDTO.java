package com.nutripet.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockResponseDTO {

    private Long idMovimiento;
    private Long productoId;
    private String tipo;
    private Integer cantidad;
    private LocalDateTime fechaMovimiento;
    private String motivo;
}