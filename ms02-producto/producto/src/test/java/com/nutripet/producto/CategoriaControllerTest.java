package com.nutripet.producto;

import com.nutripet.producto.controller.CategoriaController;
import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.service.CategoriaService;
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

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private CategoriaService categoriaService;

    @Test
    void testObtenerTodas() throws Exception {
        
        when(categoriaService.obtenerTodas()).thenReturn(List.of(new Categoria(1L, "Juguetes")));


        mockMvc.perform(get("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$[0].nombre").value("Juguetes")); 
    }
}