package com.nutripet.inventario.service;

import com.nutripet.inventario.dto.StockRequestDTO;
import com.nutripet.inventario.dto.StockResponseDTO;
import com.nutripet.inventario.model.Stock;
import com.nutripet.inventario.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;


    public List<StockResponseDTO> obtenerTodos() {
        return stockRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StockResponseDTO> obtenerPorId(Long id) {
        return stockRepository.findById(id).map(this::mapToDTO);
    }

    public StockResponseDTO guardar(StockRequestDTO dto) {
        Stock stock = new Stock();
        // Mapeo exacto basado en Stock.java
        stock.setIdProducto(dto.getIdProducto());
        stock.setCantidadActual(dto.getCantidadActual());
        stock.setStockMinimo(dto.getStockMinimo());
        stock.setFechaVencimiento(dto.getFechaVencimiento());
        stock.setNumeroLote(dto.getNumeroLote());
        stock.setUbicacionBodega(dto.getUbicacionBodega());

        return mapToDTO(stockRepository.save(stock));
    }

    public StockResponseDTO actualizar(Long id, StockRequestDTO dto) {
        Stock existente = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de Stock no encontrado con id: " + id));

        existente.setIdProducto(dto.getIdProducto());
        existente.setCantidadActual(dto.getCantidadActual());
        existente.setStockMinimo(dto.getStockMinimo());
        existente.setFechaVencimiento(dto.getFechaVencimiento());
        existente.setNumeroLote(dto.getNumeroLote());
        existente.setUbicacionBodega(dto.getUbicacionBodega());
        
        return mapToDTO(stockRepository.save(existente));
    }

    public void eliminar(Long id) {
        stockRepository.deleteById(id);
    }

    private StockResponseDTO mapToDTO(Stock stock) {
        return new StockResponseDTO(
                stock.getIdStock(),
                stock.getIdProducto(),
                stock.getCantidadActual(),
                stock.getStockMinimo(),
                stock.getFechaVencimiento(),
                stock.getNumeroLote(),
                stock.getUbicacionBodega()
        );
    }
}
