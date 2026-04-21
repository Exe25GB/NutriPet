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

import com.nutripet.cliente.model.Cliente;
import com.nutripet.cliente.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> clienteTodos(){
        return ResponseEntity.ok(service.clienteTodos());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long idCliente){
        return service.clientePorId(idCliente).map(
            ResponseEntity::ok
        ).orElse(
            ResponseEntity.notFound().build()
        );
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long idCliente, @Valid @RequestBody Cliente nCliente){
        return service.clientePorId(idCliente).map(
            existente -> {
                nCliente.setIdCliente(idCliente);
                return ResponseEntity.ok(service.registrarCliente(nCliente));
            }
        )
        .orElse(ResponseEntity.notFound().build());
            
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente nCliente){
        return ResponseEntity.status(201).body(service.registrarCliente(nCliente));
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idCliente){
        if (service.clientePorId(idCliente).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.eliminarClientePorId(idCliente);
        return ResponseEntity.noContent().build();
    }

}
