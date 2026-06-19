package com.nutripet.usuario.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nutripet.usuario.DTO.UserResponseDTO;
import com.nutripet.usuario.assemblers.UsuarioModelAssembler;
import com.nutripet.usuario.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

     private final UserService usuaService;

    private final UsuarioModelAssembler assembler;

    UsuarioControllerV2(UsuarioModelAssembler assembler, UserService usuaService) {
        this.assembler = assembler;
        this.usuaService = usuaService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<UserResponseDTO>> getAllUsuarios() {

        List<EntityModel<UserResponseDTO>> usuarios = usuaService.obtenerTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuarios,
                linkTo(methodOn(UsuarioControllerV2.class)
                        .getAllUsuarios())
                        .withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<UserResponseDTO> getUsuarioById(@PathVariable Long id) {

        UserResponseDTO usuario = usuaService.obtenerPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con ID: " + id));

        return assembler.toModel(usuario);
    }

    @GetMapping(value = "/tipo/{tipoUsuarioId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<UserResponseDTO>> getUsuarioByTipo(
            @PathVariable Long tipoUsuarioId) {

        List<EntityModel<UserResponseDTO>> usuarios = usuaService.buscarPorTipo(tipoUsuarioId)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuarios,
                linkTo(methodOn(UsuarioControllerV2.class)
                        .getUsuarioByTipo(tipoUsuarioId))
                        .withSelfRel());
    }

}
