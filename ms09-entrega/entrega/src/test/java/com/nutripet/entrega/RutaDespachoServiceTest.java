package com.nutripet.entrega;

import com.nutripet.entrega.dto.RutaDespachoResponseDTO;
import com.nutripet.entrega.model.RutaDespacho;
import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.repository.RutaDespachoRepository;
import com.nutripet.entrega.repository.ZonaRepository;
import com.nutripet.entrega.service.RutaDespachoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RutaDespachoServiceTest {

    @Mock
    private RutaDespachoRepository rutaDespachoRepository;

    @Mock
    private ZonaRepository zonaRepository;

    @InjectMocks
    private RutaDespachoService rutaDespachoService;

    @Test
    void testObtenerTodas() {
        RutaDespacho rutaMock = new RutaDespacho(1L, 10L, LocalDate.now(), 5L, "Programada");
        Zona zonaMock = new Zona(10L, "Valparaíso Centro");

        when(rutaDespachoRepository.findAll()).thenReturn(List.of(rutaMock));
        when(zonaRepository.findById(10L)).thenReturn(Optional.of(zonaMock));

        List<RutaDespachoResponseDTO> resultados = rutaDespachoService.obtenerTodas();

        // Validaciones
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("Programada", resultados.get(0).getEstado());
        assertEquals("Valparaíso Centro", resultados.get(0).getZonaNombre());
    }
}