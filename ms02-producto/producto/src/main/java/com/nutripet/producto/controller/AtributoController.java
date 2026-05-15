package com.nutripet.producto.controller;

import com.nutripet.producto.dto.AtributoRequestDTO;
import com.nutripet.producto.dto.AtributoResponseDTO;
import com.nutripet.producto.service.AtributoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de atributos de productos (MS-02).
 */
@RestController
@RequestMapping("/api/atributos")
@RequiredArgsConstructor
public class AtributoController {

    private final AtributoService atributoService;

    // ── LISTAR TODOS ──────────────────────────────────
    @GetMapping
    public ResponseEntity<List<AtributoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(atributoService.obtenerTodos());
    }

    // ── CREAR ATRIBUTO ────────────────────────────────
    // Utiliza @Valid para asegurar que los datos del RequestDTO sean correctos
    @PostMapping
    public ResponseEntity<AtributoResponseDTO> crear(@Valid @RequestBody AtributoRequestDTO dto) {
        AtributoResponseDTO nuevoAtributo = atributoService.guardar(dto);
        return new ResponseEntity<>(nuevoAtributo, HttpStatus.CREATED);
    }

    // ── BUSCAR POR ID ─────────────────────────────────
    // Maneja correctamente el caso donde el ID no existe devolviendo un 404
    @GetMapping("/{id}")
    public ResponseEntity<AtributoResponseDTO> buscarPorId(@PathVariable Long id) {
        return atributoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ── ELIMINAR ──────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        atributoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
