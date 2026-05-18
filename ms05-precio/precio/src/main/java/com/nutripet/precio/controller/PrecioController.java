package com.nutripet.precio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.precio.dto.PrecioRequestDTO;
import com.nutripet.precio.dto.PrecioResponseDTO;
import com.nutripet.precio.service.PrecioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/precios")
@RequiredArgsConstructor
public class PrecioController {

    private final PrecioService precioService;

    @PostMapping("/calcular")
    public ResponseEntity<PrecioResponseDTO> calcularPrecio(@Valid @RequestBody PrecioRequestDTO request) {
        return ResponseEntity.ok(precioService.calcularPrecioFinal(request));
    }

}
