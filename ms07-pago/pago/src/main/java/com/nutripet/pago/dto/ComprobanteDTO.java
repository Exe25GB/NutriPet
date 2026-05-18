package com.nutripet.pago.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobanteDTO {

    private String tipo;
    private String folio;
    private String url;

}
