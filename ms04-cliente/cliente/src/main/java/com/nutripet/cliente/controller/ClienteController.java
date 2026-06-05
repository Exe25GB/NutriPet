package com.nutripet.cliente.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.cliente.dto.ClienteRequestDTO;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.dto.DireccionRequestDTO;
import com.nutripet.cliente.dto.DireccionResponseDTO;
import com.nutripet.cliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//las respuestas se hacen en JSON automaticamente
@RestController
//asiganr la ruta base para todos los endpoints de este controlador
@RequestMapping("/api/clientes")
//anotacion de Lombok que crea un constructor con los atributos 'final'
@RequiredArgsConstructor
//anotacion de Swagger
@Tag(name = "Cliente", description = "Gestion de Cliente")
public class ClienteController {
    // Inyectamos el servicio que contiene la logica de negocio real
    private final ClienteService service;

    // ENDPOINTS DE CLIENTE
 // @GetMapping: peticiones GET - retorna la lista completa de clientes
    @GetMapping
    @Operation(summary = "Lista de los clientes", description = "Retorna la lista completa de clientes de la BD")
    public ResponseEntity<List<ClienteResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

 // @GetMapping:  peticiones GET - retorna un solo clientes con su id
    @GetMapping("/{id}")                                // @PathVariable: toma el valor de id directamente desde la URL -> http://localhost:8084/api/clientes/1
    @Operation(summary = "Busca cliente", description = "Retorna un solo cliente de la BD por ID de cliente.")
    public ResponseEntity<ClienteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

 // @PostMapping: peticiones POST - para crear nuevos registro
    @PostMapping                                 // @Valid  @RequestBody: activa las validaciones del DTO (@NotNull, @NotBlank) | transforma el JSON enviado en la petición al objeto ClienteRequestDTO
    @Operation(summary = "Crear cliente", description = "Crea un cliente para agregarlo a la BD")
    public ResponseEntity<ClienteResponseDTO> crear(@Valid  @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(201).body(service.guardar(dto));
    }

 // @PutMapping: peticiones PUT - para actualizar registro entero
    @PutMapping("/{id}")                              // @PathVariable          @Valid  @RequestBody: toma el valor de id directamente desde la URL -> http://localhost:8084/api/clientes/1 | activa las validaciones del DTO (@NotNull, @NotBlank) | transforma el JSON enviado en la petición al objeto ClienteRequestDTO
    @Operation(summary = "Actualizar cliente", description = "Actuliza los datos de un cliente en la BD con su ID (la ID no se puede mmodificar)")
    public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return service.actualizar(id, dto)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

 // @DeleteMapping: peticiones DELETE - para borrar registros físicos de la BD
    @DeleteMapping("/{id}")           // @PathVariable: toma el valor de id directamente desde la URL -> http://localhost:8084/api/clientes/1
    @Operation(summary = "Eliminar cliente", description = "Borra un cliente mas sus direcciones de la BD con el ID del cliente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINTS DE DIRECCIONES
 // @GetMapping: peticiones GET - Retorna todas las direcciones
    @GetMapping("/direcciones")
    @Operation(summary = "Lista de las dirreciones", description = "Retorna la lista completa de las dirreciones de los clientes de la BD")
    public ResponseEntity<List<DireccionResponseDTO>> obtenerTodasLasDirecciones() {
        return ResponseEntity.ok(service.obtenerTodasLasDirecciones());
    }
      

 // @GetMapping: peticiones GET - retorna una dirección en específico por su idDireccion
    @GetMapping("/direcciones/{idDireccion}")
    @Operation(summary = "Buscar una dirrecion", description = "Busca una dirrecion en base a su ID")
    public ResponseEntity<DireccionResponseDTO> obtenerDireccionPorId(@PathVariable Long idDireccion){
        return service.obtenerDireccionPorId(idDireccion)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

 // @GetMapping: peticiones GET - Retorna las direcciones de un cliente con su id
    @GetMapping("/{idCliente}/direcciones")
    @Operation(summary = "Direciones de un cliente", description = "Busca todas las direcciones de un cliente, con el ID del cliente ")
    public ResponseEntity<List<DireccionResponseDTO>> listarDirecciones(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.listarDireccionesPorCliente(idCliente));
    }

 // @PostMapping: peticiones POST - para crear agraegr una dirrecion a un cliente
    @PostMapping("/{idCliente}/direcciones")                  // @PathVariable                 @Valid @RequestBody: toma el valor de id directamente desde la URL -> http://localhost:8084/api/clientes/1 | activa las validaciones del DTO (@NotNull, @NotBlank) | transforma el JSON enviado en la petición al objeto DireccionRequestDTO
    @Operation(summary = "Agregar una dirrecion a un cliente", description = "Agregar a la BD una nueva dirrecion, pero esta si o si tiene que estar asignada a un cliente existente")
    public ResponseEntity<DireccionResponseDTO> agregarDireccion(@PathVariable Long idCliente, @Valid @RequestBody DireccionRequestDTO dto) {
        return ResponseEntity.status(201).body(service.agregarDireccion(idCliente, dto));
    }

   
}
