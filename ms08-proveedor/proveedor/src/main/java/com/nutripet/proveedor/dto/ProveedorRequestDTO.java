package com.nutripet.proveedor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre de la empresa debe de ser obligatorio.")
    private String nombreEmpresa;

    @NotBlank(message = "Los datos del proveedor deben de ser obligatorio.")
    private String datosProveedor;

    @NotBlank(message = "Las condiciones del proveedor deben de ser obligatorio.")
    private String condicionesProveedor;

    @NotNull(message = "El id de el orden debe de ser obligatorio.")
    @Positive(message = "El id dl orden debe de ser positivo.")
    private Long idOrden;

    @NotNull(message = "El id de marca debe de ser obligatorio.")
    @Positive(message = "El id de la marca debe de ser positivo.")
    private Long idMarca;





}
