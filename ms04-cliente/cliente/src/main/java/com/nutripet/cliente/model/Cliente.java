package com.nutripet.cliente.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity: Define que esta clase sera mapeada como una tabla en la bd
@Entity
@Table(name="clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    
 // @Id: llave primaria autoincremental
   @Id
 // @GeneratedValue: crea una secuencia de forma automatica
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idCliente;

 // @Column: lo vuelve columan
   @Column(nullable = false)
   private Long idUsuario;

   // Guarda el Enum como un texto plano ('MINORISTA', 'MAYORISTA') en lugar de un número (0, 1).
 // @Enumerated: 
   @Enumerated(EnumType.STRING)
 // @Column: 
   @Column(nullable = false)
   private TipoCliente tipoCliente;

 // @Column: 
   @Column(length = 100)
   private String razonSocial;

 // @Column: 
   @Column(nullable = false)
   private String telefono;

   // Relación Uno-a-Muchos: Un cliente puede tener múltiples direcciones.
   // 'mappedBy = "cliente"': Indica que la relación física la domina la clase DireccionEnvio.
 // @OneToMany: RELACION 1:N - 
   @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true) 
   private List<DireccionEnvio> direccionDespacho = new ArrayList<>(); // Se inicializa para evitar NullPointerException.
    

}
