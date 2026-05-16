package com.nutripet.proveedor.service;

import com.nutripet.proveedor.dto.OrdenCompraRequestDTO;
import com.nutripet.proveedor.dto.OrdenCompraResponseDTO;
import com.nutripet.proveedor.model.OrdenCompra;
import com.nutripet.proveedor.model.Proveedor;
import com.nutripet.proveedor.repository.OrdenCompraRepository;
import com.nutripet.proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;

    private OrdenCompraResponseDTO mapToDTO(OrdenCompra orden) {
        String nombreEmpresa = proveedorRepository.findById(orden.getProveedorId())
                .map(Proveedor::getNombreEmpresa)
                .orElse("Proveedor Desconocido o Eliminado");

        return new OrdenCompraResponseDTO(
                orden.getIdOrden(),
                orden.getProveedorId(),
                nombreEmpresa, 
                orden.getEstado()
        );
    }

    public List<OrdenCompraResponseDTO> obtenerTodas() {
        return ordenCompraRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrdenCompraResponseDTO> obtenerPorId(Long id) {
        return ordenCompraRepository.findById(id).map(this::mapToDTO);
    }

    public OrdenCompraResponseDTO guardar(OrdenCompraRequestDTO dto) {

        proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("No se puede generar la orden. Proveedor no encontrado con id: " + dto.getProveedorId()));

        OrdenCompra orden = new OrdenCompra();
        orden.setProveedorId(dto.getProveedorId());
        orden.setEstado(dto.getEstado());

        return mapToDTO(ordenCompraRepository.save(orden));
    }

    public OrdenCompraResponseDTO actualizar(Long id, OrdenCompraRequestDTO dto) {
        return ordenCompraRepository.findById(id).map(existente -> {
            proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + dto.getProveedorId()));
            
            existente.setProveedorId(dto.getProveedorId());
            existente.setEstado(dto.getEstado());
            
            return mapToDTO(ordenCompraRepository.save(existente));
        }).orElseThrow(() -> new RuntimeException("Orden de compra no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        if (!ordenCompraRepository.existsById(id)) {
            throw new RuntimeException("Orden de compra no encontrada con id: " + id);
        }
        ordenCompraRepository.deleteById(id);
    }

    public List<OrdenCompraResponseDTO> buscarPorProveedor(Long proveedorId) {
        return ordenCompraRepository.findByProveedorId(proveedorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
