package com.nutripet.inventario;

import com.nutripet.inventario.controller.MovimientoStockController;
import com.nutripet.inventario.dto.MovimientoStockResponseDTO;
import com.nutripet.inventario.service.MovimientoStockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovimientoStockController.class)
class MovimientoStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private MovimientoStockService movimientoStockService;

    @Test
    void testObtenerTodos() throws Exception {
        MovimientoStockResponseDTO dtoMock = new MovimientoStockResponseDTO(1L, 100L, "SALIDA", 5, LocalDateTime.now(), "Venta");
        when(movimientoStockService.obtenerTodos()).thenReturn(List.of(dtoMock));

   
        mockMvc.perform(get("/api/movimientos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value("SALIDA"))
                .andExpect(jsonPath("$[0].cantidad").value(5))
                .andExpect(jsonPath("$[0].motivo").value("Venta"));
    }
}