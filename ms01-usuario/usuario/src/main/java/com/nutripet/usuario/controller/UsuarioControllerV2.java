package com.nutripet.usuario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.nutripet.usuario.assemblers.UsuarioModelAssembler;
import com.nutripet.usuario.model.User;
import com.nutripet.usuario.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    @Autowired
    private UserService usuaService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<User>> getAllUsuarios(){
        List<EntityModel<User>> usuario = usuaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withSelfRel());
        
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<User>> getUsuarioById(@PathVariable Long id){
        User usuario = usuaService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id))
        return assembler.toModel(usuario);
    }
    

    @GetMapping(value = "/tipo/{tipoUsuarioId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<User>> getUsuarioByTipo(@PathVariable Long tipoUsuarioId){
        List<EntityModel<User>> usuario = usuaService.buscarPorTipo(tipoUsuarioId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByTipo(tipoUsuarioId)).withSelfRel());
    }
    
    
    

    
    
    

}
