package com.nutripet.entrega.model;
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
@Table(name="asignaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignacion_Repartidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacion;
    
    //Clave Foránea??
    @JoinColumn(nullable = false)
    private Long idUsuario;

    //Clave foránea 👍
    @JoinColumn(nullable = false)
    private Long idRuta;



}
