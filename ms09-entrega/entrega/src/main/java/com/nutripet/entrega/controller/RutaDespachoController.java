package com.nutripet.entrega.controller;

import com.nutripet.entrega.dto.RutaDespachoRequestDTO;
import com.nutripet.entrega.dto.RutaDespachoResponseDTO;
import com.nutripet.entrega.service.RutaDespachoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas-despacho")
@RequiredArgsConstructor
@Tag(name = "Ruta de despacho", description = "Operaciones relacionado a rutas de despacho.")
public class RutaDespachoController {

    private final RutaDespachoService rutaDespachoService;

    @GetMapping
    @Operation(summary = "Obtener todos las rutas de despacho.", description = "Lista todos los datos de rutas de despacho.")
    public ResponseEntity<List<RutaDespachoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(rutaDespachoService.obtenerTodas());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener ruta de despacho por id.", description = "Obtiene una ruta de despacho en especifico buscando por su id.")
    public ResponseEntity<RutaDespachoResponseDTO> buscarPorId(@PathVariable Long id) {
        return rutaDespachoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear ruta de despacho", description = "Crear o agregar a traves de registro de datos uno o mas rutas de despacho.")
    public ResponseEntity<RutaDespachoResponseDTO> crear(@Valid @RequestBody RutaDespachoRequestDTO dto) {
        RutaDespachoResponseDTO nuevaRuta = rutaDespachoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRuta); 
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ruta de despacho", description = "Actualiza o modifica un registro de ruta de despacho en especifico por id.")
    public ResponseEntity<RutaDespachoResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody RutaDespachoRequestDTO dto) {
        
        return rutaDespachoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Busqueda de ruta de despacho", description = "Obtiene uno o mas rutas de despacho buscando desde el id del proveedor.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return rutaDespachoService.obtenerPorId(id)
                .map(ruta -> {
                    rutaDespachoService.eliminar(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
