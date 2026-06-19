package com.nutripet.cliente.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nutripet.cliente.controller.ClienteControllerV2;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.dto.DireccionResponseDTO;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteResponseDTO, EntityModel<ClienteResponseDTO>>{
    
    @Override
    public EntityModel<ClienteResponseDTO> toModel(ClienteResponseDTO cliente) {
        return EntityModel.of(cliente,
                // Todos los clientes
                linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withRel("clientes")
        );
    }

    public EntityModel<DireccionResponseDTO> toDireccionModel(DireccionResponseDTO direccion) {
        return EntityModel.of(direccion,
                // Direccion especifica
                linkTo(methodOn(ClienteControllerV2.class).getDireccionById(direccion.getIdDireccion())).withSelfRel(),
                // Todas las direcciones de un cliente específico
                linkTo(methodOn(ClienteControllerV2.class).getDireccionesByClienteId(direccion.getIdCliente())).withRel("direccionesCliente")
        );
    }

}
