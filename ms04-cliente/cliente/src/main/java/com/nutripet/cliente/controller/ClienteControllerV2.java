package com.nutripet.cliente.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.nutripet.cliente.assemblers.ClienteModelAssembler;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.service.ClienteService;

@RestController
@RequestMapping("api/v2/cliente")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ClienteResponseDTO>> getAllCliente(){
        List<EntityModel<ClienteResponseDTO>> clientes = service.obtenerTodos().stream()
                                                                            .map(assembler::toModel)
                                                                            .collect(Collectors.toList());
        return CollectionModel.of(clientes,
            linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withSelfRel());
    }


}