package com.nutripet.pago.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutripet.pago.dto.ComprobanteDTO;
import com.nutripet.pago.dto.PagoRequestDTO;
import com.nutripet.pago.dto.PagoResponseDTO;
import com.nutripet.pago.model.Estado;
import com.nutripet.pago.model.TipoDocumento;
import com.nutripet.pago.service.PagoService;

@WebMvcTest(PagoController.class)
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Usamos MockitoBean por estar en Spring Boot 4.0.5
    @MockitoBean
    private PagoService pagoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void procesarPago_RetornaHttp201() throws Exception {
        // Arrange
        PagoRequestDTO request = new PagoRequestDTO(100L, new BigDecimal("25000.00"), 1L, TipoDocumento.BOLETA);
        
        ComprobanteDTO comprobante = new ComprobanteDTO("BOLETA", "SII-12345", "https://url.com");
        PagoResponseDTO response = new PagoResponseDTO(50L, 100L, Estado.APROBADO, new BigDecimal("25000.00"), LocalDateTime.now(), comprobante);

        when(pagoService.procesarPago(any(PagoRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/pagos/procesar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTransaccion").value(50))
                .andExpect(jsonPath("$.estado").value("APROBADO"))
                .andExpect(jsonPath("$.comprobante.folio").value("SII-12345"));
    }

    @Test
    void procesarPago_RetornaHttp400_SiMontoEsNegativo() throws Exception {
        // Arrange: Enviamos un monto de -5000 para forzar el fallo del @Positive
        PagoRequestDTO request = new PagoRequestDTO(100L, new BigDecimal("-5000.00"), 1L, TipoDocumento.BOLETA);

        // Act & Assert
        mockMvc.perform(post("/api/pagos/procesar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.montoTotal").value("El monto debe ser mayor a 0"));
    }
}