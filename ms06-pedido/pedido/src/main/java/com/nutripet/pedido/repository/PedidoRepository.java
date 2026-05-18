package com.nutripet.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutripet.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("SELECT DISTINCT p FROM Pedido p JOIN p.detalles d WHERE d.idProducto = :idProducto")
    List<Pedido> findPedidosPorProducto(@Param("idProducto") Long idProducto);


}
