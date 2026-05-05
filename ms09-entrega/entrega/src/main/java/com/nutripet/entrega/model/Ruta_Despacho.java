package com.nutripet.entrega.model;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rutas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruta_Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    @JoinColumn(nullable = false)
    private Long idZona;

    @Column(nullable = false)
    private LocalDateTime fechaRuta;

}
