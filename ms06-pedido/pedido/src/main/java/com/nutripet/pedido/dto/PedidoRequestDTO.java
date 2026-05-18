package com.nutripet.pedido.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    @NotNull(message = "El idCliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "El idDireccionEnvio es obligatorio")
    private Long idDireccionEnvio;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid
    private List<DetalleRequestDTO> productos;

}
