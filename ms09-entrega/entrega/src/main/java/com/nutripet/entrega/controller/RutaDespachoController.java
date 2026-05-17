package com.nutripet.entrega.controller;

import com.nutripet.entrega.dto.RutaDespachoRequestDTO;
import com.nutripet.entrega.dto.RutaDespachoResponseDTO;
import com.nutripet.entrega.service.RutaDespachoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas-despacho")
@RequiredArgsConstructor
public class RutaDespachoController {

    private final RutaDespachoService rutaDespachoService;

    @GetMapping
    public ResponseEntity<List<RutaDespachoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(rutaDespachoService.obtenerTodas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RutaDespachoResponseDTO> buscarPorId(@PathVariable Long id) {
        return rutaDespachoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RutaDespachoResponseDTO> crear(@Valid @RequestBody RutaDespachoRequestDTO dto) {
        RutaDespachoResponseDTO nuevaRuta = rutaDespachoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRuta); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutaDespachoResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody RutaDespachoRequestDTO dto) {
        
        return rutaDespachoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return rutaDespachoService.obtenerPorId(id)
                .map(ruta -> {
                    rutaDespachoService.eliminar(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
