package com.nutripet.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockRequestDTO {

    @NotNull(message = "El idProducto es obligatorio para registrar el movimiento")
    @Positive(message = "El idProducto debe ser un número positivo")
    private Long productoId;

    @NotBlank(message = "El tipo de movimiento es obligatorio (ej. ENTRADA, SALIDA)")
    private String tipo;

    @NotNull(message = "La cantidad del movimiento es obligatoria")
    @Positive(message = "La cantidad del movimiento debe ser mayor a 0")
    private Integer cantidad;

    @NotBlank(message = "El motivo del movimiento es obligatorio")
    private String motivo; 
    
}
