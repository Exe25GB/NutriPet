package com.nutripet.pedido.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutripet.pedido.dto.DetalleRequestDTO;
import com.nutripet.pedido.dto.DetalleResponseDTO;
import com.nutripet.pedido.dto.PedidoRequestDTO;
import com.nutripet.pedido.dto.PedidoResponseDTO;
import com.nutripet.pedido.model.Estado;
import com.nutripet.pedido.service.PedidoService;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearPedido_RetornaHttp201() throws Exception {
        // Arrange
        DetalleRequestDTO detalleReq = new DetalleRequestDTO(10L, 2, new BigDecimal("5000.00"));
        PedidoRequestDTO request = new PedidoRequestDTO(1L, 2L, List.of(detalleReq));

        DetalleResponseDTO detalleRes = new DetalleResponseDTO(10L, 2, new BigDecimal("10000.00"));
        PedidoResponseDTO response = new PedidoResponseDTO(100L, 1L, LocalDateTime.now(), new BigDecimal("10000.00"), Estado.PENDIENTE, List.of(detalleRes));

        when(pedidoService.crearPedido(any(PedidoRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPedido").value(100))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void crearPedido_RetornaHttp400_SiListaProductosEstaVacia() throws Exception {
        // Arrange: Enviamos una lista vacía para que falle el @NotEmpty
        PedidoRequestDTO request = new PedidoRequestDTO(1L, 2L, Collections.emptyList());

        // Act & Assert
        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.productos").value("El pedido debe tener al menos un producto"));
    }

    @Test
    void buscarPorProducto_RetornaHttp200() throws Exception {
        // Arrange
        PedidoResponseDTO response = new PedidoResponseDTO(100L, 1L, LocalDateTime.now(), new BigDecimal("10000.00"), Estado.PENDIENTE, Collections.emptyList());
        when(pedidoService.buscarPorProducto(10L)).thenReturn(List.of(response));

        // Act & Assert
        mockMvc.perform(get("/api/pedidos/producto/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPedido").value(100));
    }
}