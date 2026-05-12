package com.nutripet.inventario.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioRequestDTO {

    @NotNull(message = "El id del producto es obligatorio.")
    @Positive(message = "El id del producto debe de ser positivo.")
    private Long idProducto;

    @NotNull(message = "El total de stock debe de ser obligatorio.")
    @Positive(message = "El total de stock debe de ser mayor a cero.")
    private Integer stockTotalInventario;

    @NotNull(message = "El id de lote es obligatorio.")
    @Positive(message = "El id de categoria debe de ser positivo.")
    private Long idLote;

    @NotNull(message = "El id de movimiento es obligatorio.")
    @Positive(message = "El id de movimiento debe de ser positivo.")
    private Long idMovimiento;


}
