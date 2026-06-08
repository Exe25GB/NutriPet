package com.nutripet.producto.controller;

import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "Operaciones relacionados con categorias.")
public class CategoriaController {

    private final CategoriaService categoriaService;

    
    @GetMapping
    @Operation(summary = "Obtener todas las categorias.", description = "Obtiene todos los datos de la lista Categoria.")
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoria por id", description = "Obtiene un dato en especifico por el id de una categoria en especifico.")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    @Operation(summary = "Crear categoria", description = "Crear o agregar a traves de registro de datos una o mas categorias.")
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        Categoria nueva = categoriaService.guardar(categoria);
        return ResponseEntity.status(201).body(nueva);
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoria", description = "Actualiza o modifica un registro de una categoria en especifico por id.")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria datos) {
        return categoriaService.obtenerPorId(id)
                .map(existente -> {
                    datos.setIdCategoria(id); 
                    return ResponseEntity.ok(categoriaService.guardar(datos));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categorias por id", description = "Elimina permanentemente un registro de una categoria por id.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
