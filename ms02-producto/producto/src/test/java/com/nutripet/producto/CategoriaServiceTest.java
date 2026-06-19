package com.nutripet.producto;

import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.repository.CategoriaRepository;
import com.nutripet.producto.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void testObtenerTodas() {
        when(categoriaRepository.findAll()).thenReturn(List.of(new Categoria(1L, "Alimentos")));

        List<Categoria> categorias = categoriaService.obtenerTodas();

        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        assertEquals("Alimentos", categorias.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        Categoria cat = new Categoria(1L, "Accesorios");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(cat));

        Optional<Categoria> resultado = categoriaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Accesorios", resultado.get().getNombre());
    }
}
