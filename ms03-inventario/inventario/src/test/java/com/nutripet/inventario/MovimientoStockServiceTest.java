package com.nutripet.inventario;

import com.nutripet.inventario.dto.MovimientoStockResponseDTO;
import com.nutripet.inventario.model.MovimientoStock;
import com.nutripet.inventario.repository.MovimientoStockRepository;
import com.nutripet.inventario.service.MovimientoStockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovimientoStockServiceTest {

    @Mock
    private MovimientoStockRepository movimientoStockRepository;

    @InjectMocks
    private MovimientoStockService movimientoStockService;

    @Test
    void testObtenerTodos() {
        MovimientoStock movMock = new MovimientoStock(1L, 100L, "ENTRADA", 20, LocalDateTime.now(), "Compra a proveedor");
        when(movimientoStockRepository.findAll()).thenReturn(List.of(movMock));

        List<MovimientoStockResponseDTO> resultado = movimientoStockService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ENTRADA", resultado.get(0).getTipo());
        assertEquals(20, resultado.get(0).getCantidad());
    }
}
