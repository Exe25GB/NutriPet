package com.nutripet.entrega.controller;

import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.service.ZonaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
@RequiredArgsConstructor
@Tag(name = "Zona", description = "Operaciones relacionado a zonas.")
public class ZonaController {

    private final ZonaService zonaService;

    @GetMapping
    @Operation(summary = "Obtener todos las zonas.", description = "Lista todos los datos de zonas.")
    public ResponseEntity<List<Zona>> listarTodas() {
        return ResponseEntity.ok(zonaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener zona por id.", description = "Obtiene una zona en especifico buscando por su id.")
    public ResponseEntity<Zona> buscarPorId(@PathVariable Long id) {
        return zonaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear zona", description = "Crear o agregar a traves de registro de datos uno o mas zonas.")
    public ResponseEntity<Zona> crear(@Valid @RequestBody Zona zona) {
        Zona nuevaZona = zonaService.guardar(zona);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaZona);
    }

    @PutMapping("/{id}")
        @Operation(summary = "Actualizar zona", description = "Actualiza o modifica un registro de zona en especifico por id.")
    public ResponseEntity<Zona> actualizar(@PathVariable Long id, @Valid @RequestBody Zona zona) {
        return zonaService.obtenerPorId(id)
                .map(existente -> {
                    zona.setIdZona(id);
                    return ResponseEntity.ok(zonaService.guardar(zona));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar zona", description = "Elimina permanentemente un registro de zona por id. Puede ser uno o mas.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return zonaService.obtenerPorId(id)
                .map(zona -> {
                    zonaService.eliminar(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
