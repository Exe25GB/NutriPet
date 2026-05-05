package com.nutripet.producto.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "atributos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtributo;

    @Column(nullable = false)
    private String tpMascotaAtributo;

    @Column(nullable = false)
    private String cVitalAtributo;

    @Column(nullable = false)
    private String marcaAtributo;




}
