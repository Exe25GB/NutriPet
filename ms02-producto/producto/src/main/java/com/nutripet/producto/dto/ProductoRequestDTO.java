package com.nutripet.producto.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    @NotNull(message = "El código alfanumérico debe de ser obligatorio.")
    @Positive(message = "El código alfanumérico debe ser un numero positivo.")
    private Long skuProducto;

    @NotBlank(message = "El nombre del producto debe de ser obligatorio.")
    private String nombreProducto;

    @NotNull(message = "El peso del producto debe de ser obligatorio.")
    @Positive(message = "El peso del producto debe ser positivo.")
    private BigDecimal pesoProducto;
    
    @NotBlank(message = "El nombre de la descripción debe de ser obligatorio.")
    private String descripProducto;

    @NotNull(message = "El id de la categoria es obligatorio.")
    @Positive(message = "El id de la categoria debe de ser positivo.")
    private long idCategoria;




}
