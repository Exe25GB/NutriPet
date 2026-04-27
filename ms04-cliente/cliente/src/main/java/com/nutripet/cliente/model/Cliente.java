package com.nutripet.cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    //>id_usuario< es una clave forania de la Clase Usuario del ms01-usuario

    // True = Mayorista, False = Minorista
    @NotBlank(message = "Se tiene que definir si es 'MINORISTA' o 'MAYORISTA'")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;

    @Size(max = 100, message = "La razón social no puede exceder los 100 caracteres")
    @Column(name = "razon_social", nullable = true, length = 100)
    private String razonSocial;

    @NotBlank(message = "La direccion de despacho es obligatorio")
    @Size(max = 250, message = "La dirrecion es demasiado larga")
    @Column(name = "direccio_despacho", nullable = false, length = 250)
    private String direccionDespacho;


}
