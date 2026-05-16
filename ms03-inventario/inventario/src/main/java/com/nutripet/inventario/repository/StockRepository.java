package com.nutripet.inventario.repository;

import com.nutripet.inventario.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByIdProductoAndCantidadActualGreaterThanOrderByFechaVencimientoAsc(Long idProducto, Integer cantidad);

    @Query("SELECT COALESCE(SUM(s.cantidadActual), 0) FROM Stock s WHERE s.idProducto = :idProducto")
    Integer sumarStockTotalPorProducto(@Param("idProducto") Long idProducto);
}
