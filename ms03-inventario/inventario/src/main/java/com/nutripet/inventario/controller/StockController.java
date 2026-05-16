package com.nutripet.inventario.controller;

import com.nutripet.inventario.dto.StockRequestDTO;
import com.nutripet.inventario.dto.StockResponseDTO;
import com.nutripet.inventario.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> obtenerTodos() {
        List<StockResponseDTO> stockList = stockService.obtenerTodos();
        return ResponseEntity.ok(stockList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> obtenerPorId(@PathVariable Long id) {
        return stockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> guardar(@Valid @RequestBody StockRequestDTO dto) {
        StockResponseDTO nuevoStock = stockService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody StockRequestDTO dto) {
        

        StockResponseDTO stockActualizado = stockService.actualizar(id, dto);
        return ResponseEntity.ok(stockActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        stockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}