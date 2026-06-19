package com.nutripet.cliente.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutripet.cliente.dto.ClienteRequestDTO;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.model.TipoCliente;
import com.nutripet.cliente.service.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crear_RetornaHttp201() throws Exception {
        // Arrange
        ClienteRequestDTO request = new ClienteRequestDTO(1L, TipoCliente.MAYORISTA, "Empresa S.A.", "+56987654321");
        ClienteResponseDTO response = new ClienteResponseDTO(15L, 1L, TipoCliente.MAYORISTA, "Empresa S.A.", "+56987654321");

        when(clienteService.guardar(any(ClienteRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCliente").value(15))
                .andExpect(jsonPath("$.razonSocial").value("Empresa S.A."));
    }

    @Test
    void crear_RetornaHttp400_SiFaltaTelefono() throws Exception {
        // Arrange: Enviamos un RequestDTO con el teléfono vacío (rompe la validación @NotBlank)
        ClienteRequestDTO request = new ClienteRequestDTO(1L, TipoCliente.MAYORISTA, "Empresa S.A.", "");

        // Act & Assert
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.telefono").value("El teléfono es obligatorio"));
    }
}