package com.nutripet.precio.dto;

import com.nutripet.precio.model.TipoCliente;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecioRequestDTO {
    @NotNull(message = "El id del producto es obligatorio")
    private Long idProducto;

    //@NotNull(message = "El id del cliente es obligatorio")
    //private Long idCliente;

    @NotNull(message = "El tipo de cliente es obligatorio")
    private TipoCliente tipoCliente;

}
