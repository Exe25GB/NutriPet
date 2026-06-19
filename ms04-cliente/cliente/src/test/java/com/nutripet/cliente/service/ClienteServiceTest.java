package com.nutripet.cliente.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nutripet.cliente.dto.ClienteRequestDTO;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.model.Cliente;
import com.nutripet.cliente.model.TipoCliente;
import com.nutripet.cliente.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteRequestDTO requestDTO;
    private Cliente clienteGuardado;

    @BeforeEach
    void setUp() {
        requestDTO = new ClienteRequestDTO(1L, TipoCliente.MINORISTA, "NutriPet Local", "+56912345678");
        clienteGuardado = new Cliente(10L, 1L, TipoCliente.MINORISTA, "NutriPet Local", "+56912345678", new ArrayList<>());
    }

    @Test
    void guardar_Exitoso() {
        // Arrange
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardado);

        // Act
        ClienteResponseDTO response = clienteService.guardar(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(10L, response.getIdCliente());
        assertEquals("NutriPet Local", response.getRazonSocial());
        assertEquals(TipoCliente.MINORISTA, response.getTipoCliente());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void obtenerPorId_RetornaClienteSiExiste() {
        // Arrange
        when(clienteRepository.findById(10L)).thenReturn(Optional.of(clienteGuardado));

        // Act
        Optional<ClienteResponseDTO> response = clienteService.obtenerPorId(10L);

        // Assert
        assertTrue(response.isPresent());
        assertEquals(10L, response.get().getIdCliente());
    }

    @Test
    void obtenerPorId_RetornaVacioSiNoExiste() {
        // Arrange
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<ClienteResponseDTO> response = clienteService.obtenerPorId(99L);

        // Assert
        assertFalse(response.isPresent());
    }
}