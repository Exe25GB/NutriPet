package com.nutripet.pago.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutripet.pago.dto.PagoRequestDTO;
import com.nutripet.pago.dto.PagoResponseDTO;
import com.nutripet.pago.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Tag(name = "Pago", description = "Procesa el pago")
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/procesar")
    @Operation(summary = "Ver voletas", description = "Muestra la lista de pagos de la BD")
    public ResponseEntity<PagoResponseDTO> procesarPago(@Valid @RequestBody PagoRequestDTO request) {
        return ResponseEntity.status(201).body(pagoService.procesarPago(request));
    }

}
