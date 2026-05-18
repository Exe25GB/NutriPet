package com.nutripet.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.nutripet.pedido.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {
    private Long idPedido;
    private Long idCliente;
    private LocalDateTime fechaCreacion;
    private BigDecimal total;
    private Estado estado;
    private List<DetalleResponseDTO> detalles;

}
