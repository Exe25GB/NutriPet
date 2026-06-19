package com.nutripet.entrega;

import com.nutripet.entrega.controller.RutaDespachoController;
import com.nutripet.entrega.dto.RutaDespachoResponseDTO;
import com.nutripet.entrega.service.RutaDespachoService;
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

@WebMvcTest(RutaDespachoController.class)
class RutaDespachoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private RutaDespachoService rutaDespachoService;

    @Test
    void testListarTodas() throws Exception {
        RutaDespachoResponseDTO dtoMock = new RutaDespachoResponseDTO(1L, 10L, "Valparaíso Norte", LocalDate.now(), 5L, "Pendiente de Carga");
        when(rutaDespachoService.obtenerTodas()).thenReturn(List.of(dtoMock));

        mockMvc.perform(get("/api/rutas-despacho")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Pendiente de Carga"))
                .andExpect(jsonPath("$[0].zonaNombre").value("Valparaíso Norte"));
    }
}