package com.nutripet.proveedor;

import com.nutripet.proveedor.dto.ProveedorResponseDTO;
import com.nutripet.proveedor.model.Proveedor;
import com.nutripet.proveedor.repository.ProveedorRepository;
import com.nutripet.proveedor.service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void testObtenerTodos() {
        Proveedor proveedorMock = new Proveedor(1L, "Distribuidora Mascotas", "contacto@mascotas.cl", "Pago a 30 días", "Pedigree, Whiskas");
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedorMock)); // 

        List<ProveedorResponseDTO> resultado = proveedorService.obtenerTodos();

        assertNotNull(resultado); 
        assertEquals(1, resultado.size()); 
        assertEquals("Distribuidora Mascotas", resultado.get(0).getNombreEmpresa());
    }
}