package com.nutripet.proveedor.controller;

import com.nutripet.proveedor.dto.OrdenCompraRequestDTO;
import com.nutripet.proveedor.dto.OrdenCompraResponseDTO;
import com.nutripet.proveedor.service.OrdenCompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
@RequiredArgsConstructor
@Tag(name = "Ordenes de compra", description = "Operaciones relacionado a ordenes de compra")
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos.", description = "Lista todos los datos de orden de compra.")
    public ResponseEntity<List<OrdenCompraResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(ordenCompraService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener orden de compra por id.", description = "Obtiene un orden de compra en especifico buscando por su id.")
    public ResponseEntity<OrdenCompraResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ordenCompraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear orden de compra", description = "Crear o agregar a traves de registro de datos uno o mas ordenes de compra.")
    public ResponseEntity<OrdenCompraResponseDTO> guardar(@Valid @RequestBody OrdenCompraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenCompraService.guardar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar orden de compra", description = "Actualiza o modifica un registro de orden de compra en especifico por id.")
    public ResponseEntity<OrdenCompraResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody OrdenCompraRequestDTO dto) {
        return ResponseEntity.ok(ordenCompraService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar orden de compra", description = "Elimina permanentemente un registro de orden de compra por id. Puede ser uno o mas.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proveedor/{proveedorId}")
    @Operation(summary = "Busqueda de orden de compra por proveedor", description = "Obtiene uno o mas ordenes de compra buscando desde el id del proveedor.")
    public ResponseEntity<List<OrdenCompraResponseDTO>> buscarPorProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(ordenCompraService.buscarPorProveedor(proveedorId));
    }
}
