package com.nutripet.pago.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.nutripet.pago.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {

    private Long idTransaccion;
    private Long idPedido;
    private Estado estado;
    private BigDecimal montoPagado;
    private LocalDateTime fechaPago;
    private ComprobanteDTO comprobante;

}
