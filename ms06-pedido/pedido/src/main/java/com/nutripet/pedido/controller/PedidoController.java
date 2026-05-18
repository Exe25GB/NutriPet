package com.nutripet.pedido.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.pedido.dto.PedidoRequestDTO;
import com.nutripet.pedido.dto.PedidoResponseDTO;
import com.nutripet.pedido.service.PedidoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(@Valid @RequestBody PedidoRequestDTO request) {
        return ResponseEntity.status(201).body(pedidoService.crearPedido(request));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(pedidoService.buscarPorProducto(idProducto));
    }

}
