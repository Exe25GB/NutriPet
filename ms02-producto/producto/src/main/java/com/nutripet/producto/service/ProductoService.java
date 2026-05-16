package com.nutripet.producto.service;

import com.nutripet.producto.dto.ProductoRequestDTO;
import com.nutripet.producto.dto.ProductoResponseDTO;
import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.model.Producto;
import com.nutripet.producto.repository.CategoriaRepository;
import com.nutripet.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    private ProductoResponseDTO mapToDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getIdProducto(), 
                producto.getSku(),
                producto.getNombre(),
                producto.getPeso(),
                producto.getDescripcion(),
                producto.getCategoria() != null ? producto.getCategoria().getNombre() : null,
                producto.getTipoMascota(),
                producto.getCicloVital(),
                producto.getMarca()
        );
    }


    public List<ProductoResponseDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductoResponseDTO> obtenerPorId(Long id) {
        return productoRepository.findById(id).map(this::mapToDTO);
    }

    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository
                .findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException(
                        "Categoría no encontrada con id: " + dto.getIdCategoria()));

        Producto producto = new Producto();
        producto.setSku(dto.getSku());
        producto.setNombre(dto.getNombre());
        producto.setPeso(dto.getPeso());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria); 
        producto.setTipoMascota(dto.getTipoMascota());
        producto.setCicloVital(dto.getCicloVital());
        producto.setMarca(dto.getMarca());

        return mapToDTO(productoRepository.save(producto));
    }

    public Optional<ProductoResponseDTO> actualizar(Long id, ProductoRequestDTO dto) {
        return productoRepository.findById(id).map(existente -> {
            Categoria categoria = categoriaRepository
                    .findById(dto.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException(
                            "Categoría no encontrada con id: " + dto.getIdCategoria()));
            
            existente.setSku(dto.getSku());
            existente.setNombre(dto.getNombre());
            existente.setPeso(dto.getPeso());
            existente.setDescripcion(dto.getDescripcion());
            existente.setCategoria(categoria);
            existente.setTipoMascota(dto.getTipoMascota());
            existente.setCicloVital(dto.getCicloVital());
            existente.setMarca(dto.getMarca());
            
            return mapToDTO(productoRepository.save(existente));
        });
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public Optional<ProductoResponseDTO> obtenerPorSku(String sku) {
        return productoRepository.findBySku(sku).map(this::mapToDTO);
    }
}
