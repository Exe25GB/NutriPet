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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.usuario.DTO.UserRequestDTO;
import com.nutripet.usuario.DTO.UserResponseDTO;
import com.nutripet.usuario.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = ("Operaciones relacionales con los usuarios"))
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista con todos los usuarios")
    public ResponseEntity<List<UserResponseDTO>> listar() {
        return ResponseEntity.ok(userService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        return userService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> crear(@Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {
        return userService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        userService.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<UserResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(userService.buscarPorNombre(nombre));
    }

    @GetMapping("/buscar/tipo/{tipoId}")
    public ResponseEntity<List<UserResponseDTO>> buscarPorTipo(@PathVariable Long tipoId) {
        return ResponseEntity.ok(userService.buscarPorTipo(tipoId));
    }
}
