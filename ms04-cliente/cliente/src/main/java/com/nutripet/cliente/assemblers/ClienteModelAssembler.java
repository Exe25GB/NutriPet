package com.nutripet.cliente.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nutripet.cliente.controller.ClienteControllerV2;
import com.nutripet.cliente.dto.ClienteResponseDTO;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteResponseDTO, EntityModel<ClienteResponseDTO>>{
    @Override
    public EntityModel<ClienteResponseDTO> toModel(ClienteResponseDTO cliente){
        return EntityModel.of(cliente,
            // linkTo(methodOn(ClienteControllerV2.class).getClienteByCodigo(cliente.getIdCliente())).withSelfRel(),
            linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withRel("clientes")
            );
    }

}
