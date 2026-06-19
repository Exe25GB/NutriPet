package com.nutripet.notificacion.controller;

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

import com.nutripet.notificacion.model.TipoNotificacion;
import com.nutripet.notificacion.service.TipoNotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tipos-notificaciones")
@RequiredArgsConstructor
@Tag(name = "Tipo notificaciones", description = "Operaciones relacionales con los tipos de notificaciones")
public class TipoNotificacionController {

    private final TipoNotificacionService tipoNotificacionService;

    @GetMapping
    @Operation(summary = "Obtener todos los tipos de notificaciones", description = "Obtiene una lista con todos los tipos de notificaciones")
    public ResponseEntity<List<TipoNotificacion>> listar() {
        return ResponseEntity.ok(tipoNotificacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoNotificacion> buscarPorId(@PathVariable Long id) {
        return tipoNotificacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoNotificacion> crear(@RequestBody TipoNotificacion tipoNotificacion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tipoNotificacionService.guardar(tipoNotificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoNotificacion> actualizar(@PathVariable Long id, @RequestBody TipoNotificacion tipoNotificacion) {
        return tipoNotificacionService.obtenerPorId(id)
                .map(existente -> {
                    tipoNotificacion.setId(id);
                    return ResponseEntity.ok(tipoNotificacionService.guardar(tipoNotificacion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return tipoNotificacionService.obtenerPorId(id)
                .map(tipo -> {
                    tipoNotificacionService.eliminar(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
