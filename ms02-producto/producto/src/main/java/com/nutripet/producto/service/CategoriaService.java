package com.nutripet.producto.service;

import com.nutripet.producto.dto.CategoriaRequestDTO;
import com.nutripet.producto.dto.CategoriaResponseDTO;
import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // ── MAPEO PRIVADO: Entidad → ResponseDTO ─────────
    private CategoriaResponseDTO mapToDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria()
        );
    }

    public List<CategoriaResponseDTO> obtenerTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaResponseDTO> obtenerPorId(Long id) {
        return categoriaRepository.findById(id).map(this::mapToDTO);
    }

    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria(
                null, // ID autogenerado por MySQL
                dto.getNombreCategoria()
        );
        return mapToDTO(categoriaRepository.save(categoria));
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}