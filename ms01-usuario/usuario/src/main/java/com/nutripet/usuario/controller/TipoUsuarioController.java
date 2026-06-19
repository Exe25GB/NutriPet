package com.nutripet.usuario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.usuario.model.TipoUsuario;
import com.nutripet.usuario.service.TipoUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tipos-usuarios")
@Tag(name = "TIpo Usuarios", description = "Operaiones tipo de usuarios") 
@RequiredArgsConstructor
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los tipos de usuarios", description = "Obtiene una lista con todos los tipos de usuarios")
    public ResponseEntity<List<TipoUsuario>> listar() {
        return ResponseEntity.ok(tipoUsuarioService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarPorId(@PathVariable Long id) {
        return tipoUsuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> crear(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tipoUsuarioService.guardar(tipoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizar(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        return tipoUsuarioService.obtenerPorId(id)
                .map(existente -> {
                    tipoUsuario.setId(id);
                    return ResponseEntity.ok(tipoUsuarioService.guardar(tipoUsuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return tipoUsuarioService.obtenerPorId(id)
                .map(tipo -> {
                    tipoUsuarioService.eliminar(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
