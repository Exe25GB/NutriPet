package com.nutripet.inventario.controller;

import com.nutripet.inventario.dto.MovimientoStockRequestDTO;
import com.nutripet.inventario.dto.MovimientoStockResponseDTO;
import com.nutripet.inventario.service.MovimientoStockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Tag(name = "Movimiento Stock", description = "Operaciones relacionadas a movimientos stocks")
public class MovimientoStockController {

    private final MovimientoStockService movimientoStockService;

    @GetMapping
    @Operation(summary = "Obtener todos los datos de movimientos stocks.", description = "Obtiene todos los datos de la lista movimientos stocks.")
    public ResponseEntity<List<MovimientoStockResponseDTO>> obtenerTodos() {
        List<MovimientoStockResponseDTO> movimientos = movimientoStockService.obtenerTodos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id un dato movimiento stock.", description = "\"Obtiene un dato en especifico por el id de un movimiento stock en especifico")
    public ResponseEntity<MovimientoStockResponseDTO> obtenerPorId(@PathVariable Long id) {
        return movimientoStockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
        @Operation(summary = "Crear movimiento stock", description = "Crear o agregar a traves de registro de datos uno o mas movimientos.")
    public ResponseEntity<MovimientoStockResponseDTO> registrarMovimiento(
            @Valid @RequestBody MovimientoStockRequestDTO dto) {
        
        MovimientoStockResponseDTO nuevoMovimiento = movimientoStockService.registrarMovimiento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un movimiento stock por id", description = "Elimina permanentemente un registro de movimiento stock por id. Puede ser uno o mas.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoStockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}