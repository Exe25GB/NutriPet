package com.nutripet.pedido.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleResponseDTO {
    private Long idProducto;
    private Integer cantidad;
    private BigDecimal subtotal;

}
