package com.nutripet.cliente.dto;

import com.nutripet.cliente.model.TipoCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {
    @NotNull(message = "El usuario tiene que estar registrado")
    private Long idUsuario;

    @NotBlank(message = "Se tiene que definir si es 'MINORISTA' o 'MAYORISTA'")
    private TipoCliente tipoCliente;

    @Size(max = 100, message = "La razón social no puede exceder los 100 caracteres")
    private String razonSocial;

    @NotBlank(message = "La direccion de despacho es obligatorio")
    @Size(max = 250, message = "La dirrecion es demasiado larga")
    private String dirrecionDespacho;

}
