package com.nutripet.producto.service;

import com.nutripet.producto.dto.ProductoRequestDTO;
import com.nutripet.producto.dto.ProductoResponseDTO;
import com.nutripet.producto.model.Atributo;
import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.model.Producto;
import com.nutripet.producto.repository.AtributoRepository;
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
    private final AtributoRepository atributoRepository;


    private ProductoResponseDTO mapToDTO(Producto producto) {
        String nombreCat = categoriaRepository.findById(producto.getIdCategoria())
                .map(Categoria::getNombreCategoria).orElse("Sin Categoría");
        
        String tipoAttr = atributoRepository.findById(producto.getIdAtributo())
                .map(Atributo::getTpMascotaAtributo).orElse("N/A");

        return new ProductoResponseDTO(
                producto.getIdProducto(),
                producto.getSkuProducto(),
                producto.getNombreProducto(),
                producto.getPesoProducto(),
                producto.getDescripProducto(),
                producto.getIdCategoria(),
                producto.getIdAtributo(),
                tipoAttr,       
                nombreCat       
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
        categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getIdCategoria()));
        
        atributoRepository.findById(dto.getIdAtributo())
                .orElseThrow(() -> new RuntimeException("Atributo no encontrado con id: " + dto.getIdAtributo()));

        Producto producto = new Producto(
                null, 
                dto.getSkuProducto(),
                dto.getNombreProducto(),
                dto.getPesoProducto(),
                dto.getDescripProducto(),
                dto.getIdCategoria(),
                dto.getIdAtributo()
        );
        return mapToDTO(productoRepository.save(producto));
    }

   
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}