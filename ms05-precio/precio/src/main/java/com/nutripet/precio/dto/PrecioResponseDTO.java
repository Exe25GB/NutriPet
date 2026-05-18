package com.nutripet.precio.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecioResponseDTO {
    private Long idProducto;
    private String tipoCliente;
    private BigDecimal precioOriginal;
    private BigDecimal descuentoAplicado;
    private BigDecimal precioFinal;

}
