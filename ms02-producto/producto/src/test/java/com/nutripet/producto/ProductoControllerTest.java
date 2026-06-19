package com.nutripet.producto;

import com.nutripet.producto.controller.ProductoController;
import com.nutripet.producto.dto.ProductoResponseDTO;
import com.nutripet.producto.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean; 
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private ProductoService productoService;

    @Test
    void testObtenerTodos() throws Exception {
        ProductoResponseDTO dto = new ProductoResponseDTO
        (1L, "SKU1", "Hueso", BigDecimal.valueOf(0.5), "Hueso de goma", "Juguetes", "Perro", "Todas", "KONG");
        
        when(productoService.obtenerTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sku").value("SKU1"))
                .andExpect(jsonPath("$[0].nombre").value("Hueso"));
    }
}