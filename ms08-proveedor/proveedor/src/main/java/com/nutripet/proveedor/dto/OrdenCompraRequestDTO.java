package com.nutripet.proveedor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraRequestDTO {

    @NotNull(message = "El id del proveedor es obligatorio")
    @Positive(message = "El id del proveedor debe ser un número válido")
    private Long proveedorId;

    @NotBlank(message = "El estado de la orden es obligatorio (Ej: Generada, Recibida)")
    private String estado;
}
