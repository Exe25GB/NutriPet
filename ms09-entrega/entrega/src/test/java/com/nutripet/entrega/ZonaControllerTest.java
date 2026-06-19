package com.nutripet.entrega;

import com.nutripet.entrega.controller.ZonaController;
import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.service.ZonaService;
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

@WebMvcTest(ZonaController.class)
class ZonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private ZonaService zonaService;

    @Test
    void testListarTodas() throws Exception {
        when(zonaService.obtenerTodas()).thenReturn(List.of(new Zona(1L, "Viña del Mar Alto")));

        mockMvc.perform(get("/api/zonas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Viña del Mar Alto"));
    }
}