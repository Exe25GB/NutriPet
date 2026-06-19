package com.nutripet.proveedor;

import com.nutripet.proveedor.controller.ProveedorController;
import com.nutripet.proveedor.dto.ProveedorResponseDTO;
import com.nutripet.proveedor.service.ProveedorService;
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

@WebMvcTest(ProveedorController.class)
class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private ProveedorService proveedorService;

    @Test
    void testObtenerTodos() throws Exception {
        ProveedorResponseDTO dtoMock = new ProveedorResponseDTO(1L, "Proveedor Pets", "123456", "Contado", "Purina");
        when(proveedorService.obtenerTodos()).thenReturn(List.of(dtoMock));

        mockMvc.perform(get("/api/proveedores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreEmpresa").value("Proveedor Pets"))
                .andExpect(jsonPath("$[0].condiciones").value("Contado"));
    }
}