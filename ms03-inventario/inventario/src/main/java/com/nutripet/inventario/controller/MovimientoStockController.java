package com.nutripet.inventario.controller;

import com.nutripet.inventario.dto.MovimientoStockRequestDTO;
import com.nutripet.inventario.dto.MovimientoStockResponseDTO;
import com.nutripet.inventario.service.MovimientoStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoStockController {

    private final MovimientoStockService movimientoStockService;

    @GetMapping
    public ResponseEntity<List<MovimientoStockResponseDTO>> obtenerTodos() {
        List<MovimientoStockResponseDTO> movimientos = movimientoStockService.obtenerTodos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoStockResponseDTO> obtenerPorId(@PathVariable Long id) {
        return movimientoStockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovimientoStockResponseDTO> registrarMovimiento(
            @Valid @RequestBody MovimientoStockRequestDTO dto) {
        
        MovimientoStockResponseDTO nuevoMovimiento = movimientoStockService.registrarMovimiento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoStockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}