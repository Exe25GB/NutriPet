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

    @NotBlank(message = "El SKU es obligatorio")
    private String sku;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    private BigDecimal peso;

    private String descripcion;

    // >idCategoria< es una clave foranea de la clase categoria
    @NotNull(message = "El idCategoria es obligatorio")
    private Long idCategoria; 

    @NotBlank(message = "El tipo de mascota es obligatorio (ej. Perro, Gato)")
    private String tipoMascota;

    @NotBlank(message = "El ciclo vital es obligatorio (ej. Cachorro, Senior)")
    private String cicloVital;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;
}