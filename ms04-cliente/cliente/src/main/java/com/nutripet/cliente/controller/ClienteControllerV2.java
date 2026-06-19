package com.nutripet.cliente.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.cliente.assemblers.ClienteModelAssembler;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.dto.DireccionResponseDTO;
import com.nutripet.cliente.service.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v2/cliente")
@RequiredArgsConstructor
public class ClienteControllerV2 {

    private final ClienteService service;
    private final ClienteModelAssembler assembler;

    // Obtener todos los clientes
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ClienteResponseDTO>> getAllCliente() {
        List<EntityModel<ClienteResponseDTO>> clientes = service.obtenerTodos().stream()
                .map(assembler::toModel) // Invoca el método sobreescrito de la interfaz
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withSelfRel());
    }

    // Obtener las direcciones específicas de un cliente
    @GetMapping(value = "/{idCliente}/direcciones", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<DireccionResponseDTO>> getDireccionesByClienteId(@PathVariable Long idCliente) {
        List<EntityModel<DireccionResponseDTO>> direcciones = service.listarDireccionesPorCliente(idCliente).stream()
                .map(assembler::toDireccionModel) // Mapeo dinámico usando el método unificado
                .collect(Collectors.toList());

        return CollectionModel.of(direcciones,
                linkTo(methodOn(ClienteControllerV2.class).getDireccionesByClienteId(idCliente)).withSelfRel());
    }

    // Obtener una única direccion por su ID por separado
    @GetMapping(value = "/direcciones/{idDireccion}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DireccionResponseDTO>> getDireccionById(@PathVariable Long idDireccion) {
        return service.obtenerDireccionPorId(idDireccion)
                .map(assembler::toDireccionModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}