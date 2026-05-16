package com.nutripet.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDTO {

    @NotNull(message = "El idProducto es obligatorio")
    @Positive(message = "El idProducto debe ser un número positivo")
    private Long idProducto;

    @NotNull(message = "La cantidad actual es obligatoria")
    @PositiveOrZero(message = "La cantidad actual debe ser cero o un valor positivo")
    private Integer cantidadActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @PositiveOrZero(message = "El stock mínimo debe ser cero o un valor positivo")
    private Integer stockMinimo;

    @NotNull(message = "La fecha de vencimiento es obligatoria (Lógica FEFO)")
    private LocalDate fechaVencimiento;

    @NotBlank(message = "El número de lote no puede estar vacío")
    private String numeroLote;

    @NotBlank(message = "La ubicación de la bodega no puede estar vacía")
    private String ubicacionBodega;
}
