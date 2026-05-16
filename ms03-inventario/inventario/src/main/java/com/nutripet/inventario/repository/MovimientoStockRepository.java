package com.nutripet.inventario.repository;

import com.nutripet.inventario.model.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Long> {

    List<MovimientoStock> findByProductoIdOrderByFechaMovimientoDesc(Long productoId);
}
