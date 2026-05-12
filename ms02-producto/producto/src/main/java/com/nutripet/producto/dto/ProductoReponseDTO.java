package com.nutripet.producto.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoReponseDTO {

    private Long idProducto;
    private Long skuProducto;
    private String nombreProducto;
    private BigDecimal pesoProducto;
    private String descripProducto;
    private long idCategoria;

    private String tipoAtributo;
    private String nombreCategoria;


}


