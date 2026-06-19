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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.notificacion.DTO.NotificacionRequestDTO;
import com.nutripet.notificacion.DTO.NotificacionResponseDTO;
import com.nutripet.notificacion.service.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@Tag(name = "Notificaciones", description = "Operaciones relacionales con las notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    @Operation(summary = "Obtener todas las notificaciones", description = "Obtiene una lista con todas las notificaciones")
    public ResponseEntity<List<NotificacionResponseDTO>> listar() {
        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> buscarPorId(@PathVariable Long id) {
        return notificacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionRequestDTO dto) {
        return notificacionService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar/destinatario")
    public ResponseEntity<List<NotificacionResponseDTO>> buscarPorDestinatario(@RequestParam String texto) {
        return ResponseEntity.ok(notificacionService.buscarPorDestinatario(texto));
    }

    @GetMapping("/buscar/tipo/{tipoId}")
    public ResponseEntity<List<NotificacionResponseDTO>> buscarPorTipo(@PathVariable Long tipoId) {
        return ResponseEntity.ok(notificacionService.buscarPorTipo(tipoId));
    }

}
