package com.nutripet.inventario;

import com.nutripet.inventario.controller.StockController;
import com.nutripet.inventario.dto.StockResponseDTO;
import com.nutripet.inventario.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private StockService stockService;

    @Test
    void testObtenerTodos() throws Exception {
        StockResponseDTO dtoMock = new StockResponseDTO(1L, 100L, 50, 10, LocalDate.of(2026, 12, 31), "LOTE-123", "Pasillo A");
        when(stockService.obtenerTodos()).thenReturn(List.of(dtoMock));

        mockMvc.perform(get("/api/stock")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroLote").value("LOTE-123"))
                .andExpect(jsonPath("$[0].cantidadActual").value(50));
    }
}
