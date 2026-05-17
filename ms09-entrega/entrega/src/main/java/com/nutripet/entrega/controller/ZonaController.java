package com.nutripet.entrega.controller;

import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.service.ZonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
@RequiredArgsConstructor
public class ZonaController {

    private final ZonaService zonaService;

    @GetMapping
    public ResponseEntity<List<Zona>> listarTodas() {
        return ResponseEntity.ok(zonaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zona> buscarPorId(@PathVariable Long id) {
        return zonaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Zona> crear(@Valid @RequestBody Zona zona) {
        Zona nuevaZona = zonaService.guardar(zona);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaZona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zona> actualizar(@PathVariable Long id, @Valid @RequestBody Zona zona) {
        return zonaService.obtenerPorId(id)
                .map(existente -> {
                    zona.setIdZona(id);
                    return ResponseEntity.ok(zonaService.guardar(zona));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return zonaService.obtenerPorId(id)
                .map(zona -> {
                    zonaService.eliminar(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
