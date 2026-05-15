package com.nutripet.producto.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {

    private Long idProducto;
    private Long skuProducto;
    private String nombreProducto;
    private BigDecimal pesoProducto;
    private String descripProducto;
    private Long idCategoria;
    private Long idAtributo;

    private String tipoAtributo;
    private String nombreCategoria;


}


