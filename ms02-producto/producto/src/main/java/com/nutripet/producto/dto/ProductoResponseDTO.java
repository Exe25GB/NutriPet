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
    private String sku;
    private String nombre;
    private BigDecimal peso;
    private String descripcion;
    
    // Este dato es una clave foranea de la clase <Categoria>
    private String categoriaNombre;

    // <Atributos>
    private String tipoMascota;
    private String cicloVital;
    private String marca;
}