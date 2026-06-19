package com.nutripet.producto.assemblers;

import com.nutripet.producto.controller.ProductoControllerV2;
import com.nutripet.producto.dto.ProductoResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductoResponseDTO, EntityModel<ProductoResponseDTO>> {

    @Override
    public EntityModel<ProductoResponseDTO> toModel(ProductoResponseDTO producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).obtenerPorId(producto.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).obtenerTodos()).withRel("productos"));
    }
}