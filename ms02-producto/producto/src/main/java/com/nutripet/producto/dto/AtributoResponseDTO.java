package com.nutripet.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtributoResponseDTO {
    private Long idAtributo;
    private String tpMascotaAtributo;
    private String cVitalAtributo;
    private String marcaAtributo;
}