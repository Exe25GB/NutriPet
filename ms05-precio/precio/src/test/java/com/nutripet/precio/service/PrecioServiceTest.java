package com.nutripet.precio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nutripet.precio.dto.PrecioRequestDTO;
import com.nutripet.precio.dto.PrecioResponseDTO;
import com.nutripet.precio.model.Descuento;
import com.nutripet.precio.model.Precio;
import com.nutripet.precio.model.TipoCliente;
import com.nutripet.precio.repository.DescuentoRepository;
import com.nutripet.precio.repository.PrecioRepository;

@ExtendWith(MockitoExtension.class)
public class PrecioServiceTest {
    @Mock
    private PrecioRepository precioRepository;

    @Mock
    private DescuentoRepository descuentoRepository;

    @InjectMocks
    private PrecioService precioService;

    private PrecioRequestDTO requestDTO;
    private Precio precioBase;
    private Descuento descuento;

    @BeforeEach
    void setUp() {
        // Datos de prueba que se reinician antes de cada test
        requestDTO = new PrecioRequestDTO(1L, TipoCliente.MAYORISTA);
        precioBase = new Precio(1L, 1L, new BigDecimal("10000.00"));
        
        descuento = new Descuento();
        descuento.setPorcentajeDescuento(new BigDecimal("10.00"));
    }

    @Test
    void calcularPrecioFinal_ConDescuentoExitoso() {
        // Arrange (Preparar)
        when(precioRepository.findByIdProducto(1L)).thenReturn(List.of(precioBase));
        when(descuentoRepository.findByTipoCliente(TipoCliente.MAYORISTA)).thenReturn(List.of(descuento));

        // Act (Ejecutar)
        PrecioResponseDTO response = precioService.calcularPrecioFinal(requestDTO);

        // Assert (Verificar)
        assertNotNull(response);
        assertEquals(new BigDecimal("9000.00"), response.getPrecioFinal());
        assertEquals(new BigDecimal("1000.00"), response.getDescuentoAplicado());
    }

    @Test
    void calcularPrecioFinal_SinDescuentoAplicable() {
        // Arrange
        when(precioRepository.findByIdProducto(1L)).thenReturn(List.of(precioBase));
        when(descuentoRepository.findByTipoCliente(TipoCliente.MAYORISTA)).thenReturn(Collections.emptyList());

        // Act
        PrecioResponseDTO response = precioService.calcularPrecioFinal(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(new BigDecimal("10000.00"), response.getPrecioFinal()); // El precio se mantiene igual
        assertEquals(new BigDecimal("0.00"), response.getDescuentoAplicado());
    }

    @Test
    void calcularPrecioFinal_LanzaExcepcionSiProductoNoExiste() {
        // Arrange
        when(precioRepository.findByIdProducto(1L)).thenReturn(Collections.emptyList());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            precioService.calcularPrecioFinal(requestDTO);
        });

        assertEquals("Precio no encontrado para el producto ID: 1", exception.getMessage());
    }

}
