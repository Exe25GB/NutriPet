package com.nutripet.precio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutripet.precio.dto.PrecioRequestDTO;
import com.nutripet.precio.dto.PrecioResponseDTO;
import com.nutripet.precio.model.TipoCliente;
import com.nutripet.precio.service.PrecioService;

@MockitoBean
public class PrecioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PrecioService precioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calcularPrecio_RetornaHttp200() throws Exception {
        // Arrange
        PrecioRequestDTO request = new PrecioRequestDTO(1L, TipoCliente.MAYORISTA);
        PrecioResponseDTO response = new PrecioResponseDTO(1L, "MAYORISTA", new BigDecimal("10000.00"), new BigDecimal("1000.00"), new BigDecimal("9000.00"));

        when(precioService.calcularPrecioFinal(any(PrecioRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/precios/calcular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1))
                .andExpect(jsonPath("$.precioFinal").value(9000.00));
    }
    
    @Test
    void calcularPrecio_RetornaHttp400_SiFaltaIdProducto() throws Exception {
        // Arrange: Enviamos null en el idProducto para forzar el fallo del @NotNull
        PrecioRequestDTO request = new PrecioRequestDTO(null, TipoCliente.MAYORISTA);

        // Act & Assert
        mockMvc.perform(post("/api/precios/calcular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                // Verificamos que tu GlobalExceptionHandler devuelva el mensaje exacto
                .andExpect(jsonPath("$.idProducto").value("El id del producto es obligatorio")); 
    }
}
