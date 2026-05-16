package com.nutripet.proveedor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ═══════════════════════════════════════════════════
// DTO DE ENTRADA · PROVEEDOR
// ═══════════════════════════════════════════════════

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String nombreEmpresa;

    @NotBlank(message = "Los datos de contacto (teléfono, email) son obligatorios")
    private String datosContacto;

    private String condiciones;

    private String marcasOfrecidas;
}
