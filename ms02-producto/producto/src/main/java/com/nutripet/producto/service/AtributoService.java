package com.nutripet.producto.service;

import com.nutripet.producto.dto.AtributoRequestDTO;
import com.nutripet.producto.dto.AtributoResponseDTO;
import com.nutripet.producto.model.Atributo;
import com.nutripet.producto.repository.AtributoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtributoService {

    private final AtributoRepository atributoRepository;

    // ── MAPEO PRIVADO: Entidad → ResponseDTO ─────────
    private AtributoResponseDTO mapToDTO(Atributo atributo) {
        return new AtributoResponseDTO(
                atributo.getIdAtributo(),
                atributo.getTpMascotaAtributo(),
                atributo.getCVitalAtributo(),
                atributo.getMarcaAtributo()
        );
    }

    public List<AtributoResponseDTO> obtenerTodos() {
        return atributoRepository.findAll()
                .stream()
                .map(this::mapToDTO) // Aquí se realiza la conversión necesaria
                .collect(Collectors.toList());
    }

    public Optional<AtributoResponseDTO> obtenerPorId(Long id) {
        // Al usar .map(this::mapToDTO), el Optional pasa de ser de Atributo a AtributoResponseDTO
        return atributoRepository.findById(id).map(this::mapToDTO);
    }

    public AtributoResponseDTO guardar(AtributoRequestDTO dto) {
        Atributo atributo = new Atributo(
                null,
                dto.getTpMascotaAtributo(),
                dto.getCVitalAtributo(),
                dto.getMarcaAtributo()
        );
        return mapToDTO(atributoRepository.save(atributo));
    }

    public void eliminar(Long id) {
        atributoRepository.deleteById(id);
    }
}