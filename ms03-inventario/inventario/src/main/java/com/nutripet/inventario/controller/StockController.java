package com.nutripet.inventario.controller;

import com.nutripet.inventario.dto.StockRequestDTO;
import com.nutripet.inventario.dto.StockResponseDTO;
import com.nutripet.inventario.service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@Tag(name = "Stock", description = "Operaciones relacionado a Stocks.")
public class StockController {

    private final StockService stockService;

    @GetMapping
    @Operation(summary = "Obtener todos los stocks.", description = "Obtiene todos los datos de la lista stocks.")
    public ResponseEntity<List<StockResponseDTO>> obtenerTodos() {
        List<StockResponseDTO> stockList = stockService.obtenerTodos();
        return ResponseEntity.ok(stockList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener stock por id", description = "Obtiene un dato en especifico por el id de un stock en especifico")
    public ResponseEntity<StockResponseDTO> obtenerPorId(@PathVariable Long id) {
        return stockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear stock", description = "Crear o agregar a traves de registro de datos uno o mas stocks.")
    public ResponseEntity<StockResponseDTO> guardar(@Valid @RequestBody StockRequestDTO dto) {
        StockResponseDTO nuevoStock = stockService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoStock);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar stock", description = "Actualiza o modifica un registro de stock en especifico por id")
    public ResponseEntity<StockResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody StockRequestDTO dto) {
        

        StockResponseDTO stockActualizado = stockService.actualizar(id, dto);
        return ResponseEntity.ok(stockActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar stocks", description = "Elimina permanentemente un registro de stock por id. Puede ser uno o mas.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        stockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}