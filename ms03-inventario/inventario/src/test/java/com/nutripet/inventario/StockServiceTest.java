package com.nutripet.inventario;

import com.nutripet.inventario.dto.StockResponseDTO;
import com.nutripet.inventario.model.Stock;
import com.nutripet.inventario.repository.StockRepository;
import com.nutripet.inventario.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void testObtenerTodos() {
        Stock stockMock = new Stock(1L, 100L, 50, 10, LocalDate.of(2026, 12, 31), "LOTE-123", "Pasillo A");
        when(stockRepository.findAll()).thenReturn(List.of(stockMock));

        List<StockResponseDTO> resultado = stockService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("LOTE-123", resultado.get(0).getNumeroLote());
        assertEquals(50, resultado.get(0).getCantidadActual());
    }
}
