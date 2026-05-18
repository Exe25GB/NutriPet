package com.nutripet.pago.dto;

import java.math.BigDecimal;

import com.nutripet.pago.model.TipoDocumento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {

    @NotNull(message = "El idPedido es obligatorio")
    private Long idPedido;

    @NotNull(message = "El monto total a pagar es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private BigDecimal montoTotal;

    @NotNull(message = "El método de pago es obligatorio")
    private Long idMetodoPago;

    @NotNull(message = "Debe indicar si desea BOLETA o FACTURA")
    private TipoDocumento tipoDocumento;

}
