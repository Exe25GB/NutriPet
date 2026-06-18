package com.nutripet.producto.controller;

import com.nutripet.producto.assemblers.ProductoModelAssembler;
import com.nutripet.producto.dto.ProductoRequestDTO;
import com.nutripet.producto.dto.ProductoResponseDTO;
import com.nutripet.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
// Definimos la ruta V2 y especificamos que produce HAL JSON (el estándar de HATEOAS)
@RequestMapping(value = "/api/v2/productos", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Producto V2 (HATEOAS)", description = "Operaciones relacionadas a productos con HATEOAS")
public class ProductoControllerV2 {

    private final ProductoService productoService;
    private final ProductoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los productos.", description = "Obtiene todos los datos de la lista producto con enlaces.")
    public CollectionModel<EntityModel<ProductoResponseDTO>> obtenerTodos() {
        List<EntityModel<ProductoResponseDTO>> productos = productoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por id", description = "Obtiene un dato en especifico por el id de un producto.")
    public ResponseEntity<EntityModel<ProductoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Crear o agregar a traves de registro de datos uno o mas productos.")
    public ResponseEntity<EntityModel<ProductoResponseDTO>> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO nuevo = productoService.guardar(dto);
        EntityModel<ProductoResponseDTO> entityModel = assembler.toModel(nuevo);

        // Retornamos un 201 Created con la URI generada automáticamente por HATEOAS en los headers
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza o modifica un registro de producto en especifico por id.")
    public ResponseEntity<EntityModel<ProductoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        return productoService.actualizar(id, dto)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina permanentemente un registro de producto por id.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Obtener producto por código alfanumérico", description = "Obtiene un producto específico mediante su SKU.")
    public ResponseEntity<EntityModel<ProductoResponseDTO>> obtenerPorSku(@PathVariable String sku) {
        return productoService.obtenerPorSku(sku)
                .map(assembler::toModel)
                // Se agrega manualmente el enlace de búsqueda por SKU
                .map(model -> {
                    model.add(linkTo(methodOn(ProductoControllerV2.class).obtenerPorSku(sku)).withSelfRel());
                    return model;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}