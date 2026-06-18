package com.nutripet.usuario.assemblers;

import com.nutripet.usuario.DTO.UserResponseDTO;
import com.nutripet.usuario.controller.UsuarioControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<UserResponseDTO, EntityModel<UserResponseDTO>> {

    @Override
    public EntityModel<UserResponseDTO> toModel(UserResponseDTO usuario) {
        
        return EntityModel.of(
                usuario,

                linkTo(methodOn(UsuarioControllerV2.class)
                        .getUsuarioById(usuario.getId()))
                        .withSelfRel(),

                linkTo(methodOn(UsuarioControllerV2.class)
                        .getAllUsuarios())
                        .withRel("usuarios")
        );
    }
}


