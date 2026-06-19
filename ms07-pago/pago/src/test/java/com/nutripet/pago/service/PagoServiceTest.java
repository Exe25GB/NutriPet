package com.nutripet.pago.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nutripet.pago.dto.PagoRequestDTO;
import com.nutripet.pago.dto.PagoResponseDTO;
import com.nutripet.pago.model.Comprobante;
import com.nutripet.pago.model.Estado;
import com.nutripet.pago.model.Pago;
import com.nutripet.pago.model.TipoDocumento;
import com.nutripet.pago.model.Transaccion;
import com.nutripet.pago.repository.ComprobanteRepository;
import com.nutripet.pago.repository.PagoRepository;
import com.nutripet.pago.repository.TransaccionRepository;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private ComprobanteRepository comprobanteRepository;

    @InjectMocks
    private PagoService pagoService;

    private PagoRequestDTO requestDTO;
    private Pago metodoPago;
    private Transaccion transaccionGuardada;
    private Comprobante comprobanteGuardado;

    @BeforeEach
    void setUp() {
        // Datos de entrada
        requestDTO = new PagoRequestDTO(100L, new BigDecimal("25000.00"), 1L, TipoDocumento.BOLETA);

        // Entidad Método de Pago simulada
        metodoPago = new Pago(1L, "WebPay Plus", "WP-CL-001");

        // Entidad Transacción simulada tras guardar
        transaccionGuardada = new Transaccion(50L, 100L, new BigDecimal("25000.00"), LocalDateTime.now(), Estado.APROBADO, metodoPago);

        // Entidad Comprobante simulada tras guardar
        comprobanteGuardado = new Comprobante(10L, transaccionGuardada, TipoDocumento.BOLETA, "SII-ABC12345", "https://nutripet.cl/comprobantes/SII-ABC12345.pdf");
    }

    @Test
    void procesarPago_Exitoso() {
        // Arrange
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(metodoPago));
        when(transaccionRepository.save(any(Transaccion.class))).thenReturn(transaccionGuardada);
        when(comprobanteRepository.save(any(Comprobante.class))).thenReturn(comprobanteGuardado);

        // Act
        PagoResponseDTO response = pagoService.procesarPago(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(50L, response.getIdTransaccion());
        assertEquals(Estado.APROBADO, response.getEstado());
        assertEquals(0, new BigDecimal("25000.00").compareTo(response.getMontoPagado()));
        assertNotNull(response.getComprobante());
        assertEquals("BOLETA", response.getComprobante().getTipo());
        assertEquals("SII-ABC12345", response.getComprobante().getFolio());

        // Verificamos que los repositorios fueron llamados exactamente una vez
        verify(pagoRepository, times(1)).findById(1L);
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));
        verify(comprobanteRepository, times(1)).save(any(Comprobante.class));
    }

    @Test
    void procesarPago_FallaPorMetodoPagoInexistente() {
        // Arrange
        when(pagoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pagoService.procesarPago(requestDTO);
        });

        assertEquals("Método de pago no encontrado ID: 1", exception.getMessage());
        
        // Verificamos que si falla el método de pago, NUNCA se guarde la transacción
        verify(transaccionRepository, never()).save(any(Transaccion.class));
    }
}