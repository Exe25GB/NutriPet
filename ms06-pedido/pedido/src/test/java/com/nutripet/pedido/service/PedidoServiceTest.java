package com.nutripet.pedido.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nutripet.pedido.dto.DetalleRequestDTO;
import com.nutripet.pedido.dto.PedidoRequestDTO;
import com.nutripet.pedido.dto.PedidoResponseDTO;
import com.nutripet.pedido.model.Detalle;
import com.nutripet.pedido.model.Estado;
import com.nutripet.pedido.model.Pedido;
import com.nutripet.pedido.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private PedidoRequestDTO requestDTO;
    private Pedido pedidoGuardado;

    @BeforeEach
    void setUp() {
        // Preparamos un DTO de entrada simulando la petición del usuario
        DetalleRequestDTO detalleReq = new DetalleRequestDTO(10L, 2, new BigDecimal("5000.00"));
        requestDTO = new PedidoRequestDTO(1L, 2L, List.of(detalleReq));

        // Preparamos la entidad que "devolverá" la base de datos al guardar
        pedidoGuardado = new Pedido();
        pedidoGuardado.setIdPedido(100L);
        pedidoGuardado.setIdCliente(1L);
        pedidoGuardado.setIdDireccionEnvio(2L);
        pedidoGuardado.setFechaCreacion(LocalDateTime.now());
        pedidoGuardado.setEstado(Estado.PENDIENTE);
        pedidoGuardado.setTotalCobrar(new BigDecimal("10000.00"));
        
        Detalle detalleGuardado = new Detalle(1L, pedidoGuardado, 10L, 2, new BigDecimal("5000.00"), new BigDecimal("10000.00"));
        pedidoGuardado.setDetalles(List.of(detalleGuardado));
    }

    @Test
    void crearPedido_Exitoso() {
        // Arrange
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoGuardado);

        // Act
        PedidoResponseDTO response = pedidoService.crearPedido(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(100L, response.getIdPedido());
        assertEquals(Estado.PENDIENTE, response.getEstado());
        assertEquals(0, new BigDecimal("10000.00").compareTo(response.getTotal()));
        assertEquals(1, response.getDetalles().size());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void buscarPorProducto_RetornaLista() {
        // Arrange
        when(pedidoRepository.findPedidosPorProducto(10L)).thenReturn(List.of(pedidoGuardado));

        // Act
        List<PedidoResponseDTO> responses = pedidoService.buscarPorProducto(10L);

        // Assert
        assertFalse(responses.isEmpty());
        assertEquals(100L, responses.get(0).getIdPedido());
        verify(pedidoRepository, times(1)).findPedidosPorProducto(10L);
    }
}