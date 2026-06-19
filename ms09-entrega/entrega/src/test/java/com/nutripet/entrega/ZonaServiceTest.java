package com.nutripet.entrega;

import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.repository.ZonaRepository;
import com.nutripet.entrega.service.ZonaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZonaServiceTest {

    @Mock
    private ZonaRepository zonaRepository;

    @InjectMocks
    private ZonaService zonaService;

    @Test
    void testObtenerTodas() {
        Zona zonaMock = new Zona(1L, "Quilpué Norte");
        when(zonaRepository.findAll()).thenReturn(List.of(zonaMock));

        List<Zona> resultados = zonaService.obtenerTodas();

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("Quilpué Norte", resultados.get(0).getNombre());
    }
}