package com.nutripet.proveedor.controller;

import com.nutripet.proveedor.dto.ProveedorRequestDTO;
import com.nutripet.proveedor.dto.ProveedorResponseDTO;
import com.nutripet.proveedor.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;


    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> guardar(@Valid @RequestBody ProveedorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody ProveedorRequestDTO dto) {
        return ResponseEntity.ok(proveedorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProveedorResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(proveedorService.buscarPorNombre(nombre));
    }
}
