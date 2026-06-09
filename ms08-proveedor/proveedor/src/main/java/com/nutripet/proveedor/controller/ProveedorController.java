package com.nutripet.proveedor.controller;

import com.nutripet.proveedor.dto.ProveedorRequestDTO;
import com.nutripet.proveedor.dto.ProveedorResponseDTO;
import com.nutripet.proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Obtener todos los proveedores.", description = "Lista todos los datos de proveedores.")
    public ResponseEntity<List<ProveedorResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener información del proveedor por id.", description = "Obtiene un proveedor en especifico buscando por su id.")
    public ResponseEntity<ProveedorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear o guardar proveedor", description = "Crear o agregar a traves de registro de datos uno o mas proveedores.")
    public ResponseEntity<ProveedorResponseDTO> guardar(@Valid @RequestBody ProveedorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Actualiza o modifica un registro de proveedor en especifico por id.")
    public ResponseEntity<ProveedorResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody ProveedorRequestDTO dto) {
        return ResponseEntity.ok(proveedorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina permanentemente un registro de proveedor por id. Puede ser uno o mas.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Obtener producto por el nombre del proveedor", description = "Obtiene un proveedores en especifico buscando desde el nombre del proveedor.")
    public ResponseEntity<List<ProveedorResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(proveedorService.buscarPorNombre(nombre));
    }
}
