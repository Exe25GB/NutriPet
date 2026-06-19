package com.nutripet.proveedor;

import com.nutripet.proveedor.controller.OrdenCompraController;
import com.nutripet.proveedor.dto.OrdenCompraResponseDTO;
import com.nutripet.proveedor.service.OrdenCompraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrdenCompraController.class)
class OrdenCompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private OrdenCompraService ordenCompraService;

    @Test
    void testObtenerTodas() throws Exception {
        
        OrdenCompraResponseDTO dtoMock = new OrdenCompraResponseDTO(1L, 10L, "Proveedor Pets", "Recibida");
        when(ordenCompraService.obtenerTodas()).thenReturn(List.of(dtoMock));

        mockMvc.perform(get("/api/ordenes-compra")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Recibida"))
                .andExpect(jsonPath("$[0].nombreEmpresa").value("Proveedor Pets"));
    }
}