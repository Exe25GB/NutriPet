package com.nutripet.proveedor;

import com.nutripet.proveedor.dto.OrdenCompraResponseDTO;
import com.nutripet.proveedor.model.OrdenCompra;
import com.nutripet.proveedor.model.Proveedor;
import com.nutripet.proveedor.repository.OrdenCompraRepository;
import com.nutripet.proveedor.repository.ProveedorRepository;
import com.nutripet.proveedor.service.OrdenCompraService;
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
class OrdenCompraServiceTest {

    @Mock
    private OrdenCompraRepository ordenCompraRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private OrdenCompraService ordenCompraService;

    @Test
    void testObtenerTodas() {
        OrdenCompra ordenMock = new OrdenCompra(1L, 10L, "Generada");
        Proveedor proveedorMock = new Proveedor(10L, "Distribuidora Mascotas", "contacto@mascotas.cl", "Pago a 30 días", "Pedigree");

        when(ordenCompraRepository.findAll()).thenReturn(List.of(ordenMock));
        when(proveedorRepository.findById(10L)).thenReturn(Optional.of(proveedorMock));

        List<OrdenCompraResponseDTO> resultado = ordenCompraService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Generada", resultado.get(0).getEstado());
        assertEquals("Distribuidora Mascotas", resultado.get(0).getNombreEmpresa());
    }
}