package com.nutripet.producto;

import com.nutripet.producto.dto.ProductoResponseDTO;
import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.model.Producto;
import com.nutripet.producto.repository.CategoriaRepository;
import com.nutripet.producto.repository.ProductoRepository;
import com.nutripet.producto.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void testObtenerTodos() {

        Categoria cat = new Categoria(1L, "Alimentos");
        Producto prod = new Producto(1L, "SKU123", "Dog Chow", BigDecimal.valueOf(15.0), "Comida para perro", cat, "Perro", "Adulto", "Purina");
        
        when(productoRepository.findAll()).thenReturn(List.of(prod));

        List<ProductoResponseDTO> productos = productoService.obtenerTodos();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Dog Chow", productos.get(0).getNombre());
        assertEquals("Alimentos", productos.get(0).getCategoriaNombre()); 
    }
}