package com.nutripet.inventario.service;

import com.nutripet.inventario.dto.MovimientoStockRequestDTO;
import com.nutripet.inventario.dto.MovimientoStockResponseDTO;
import com.nutripet.inventario.model.MovimientoStock;
import com.nutripet.inventario.repository.MovimientoStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovimientoStockService {

    private final MovimientoStockRepository movimientoStockRepository;

    public List<MovimientoStockResponseDTO> obtenerTodos() {
        return movimientoStockRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MovimientoStockResponseDTO> obtenerPorId(Long id) {
        return movimientoStockRepository.findById(id).map(this::mapToDTO);
    }

    public MovimientoStockResponseDTO registrarMovimiento(MovimientoStockRequestDTO dto) {
        MovimientoStock movimiento = new MovimientoStock();
        movimiento.setProductoId(dto.getProductoId());
        movimiento.setTipo(dto.getTipo());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setMotivo(dto.getMotivo());
        
        movimiento.setFechaMovimiento(LocalDateTime.now());

        return mapToDTO(movimientoStockRepository.save(movimiento));
    }

    public void eliminar(Long id) {
        movimientoStockRepository.deleteById(id);
    }

    private MovimientoStockResponseDTO mapToDTO(MovimientoStock movimiento) {
        return new MovimientoStockResponseDTO(
                movimiento.getIdMovimiento(),
                movimiento.getProductoId(),
                movimiento.getTipo(),
                movimiento.getCantidad(),
                movimiento.getFechaMovimiento(),
                movimiento.getMotivo()
        );
    }
}
