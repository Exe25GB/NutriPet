package com.nutripet.proveedor.service;

import com.nutripet.proveedor.dto.ProveedorRequestDTO;
import com.nutripet.proveedor.dto.ProveedorResponseDTO;
import com.nutripet.proveedor.model.Proveedor;
import com.nutripet.proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    private ProveedorResponseDTO mapToDTO(Proveedor proveedor) {
        return new ProveedorResponseDTO(
                proveedor.getIdProveedor(),
                proveedor.getNombreEmpresa(),
                proveedor.getDatosContacto(),
                proveedor.getCondiciones(),
                proveedor.getMarcasOfrecidas()
        );
    }

    public List<ProveedorResponseDTO> obtenerTodos() {
        return proveedorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProveedorResponseDTO> obtenerPorId(Long id) {
        return proveedorRepository.findById(id).map(this::mapToDTO);
    }

    public ProveedorResponseDTO guardar(ProveedorRequestDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreEmpresa(dto.getNombreEmpresa());
        proveedor.setDatosContacto(dto.getDatosContacto());
        proveedor.setCondiciones(dto.getCondiciones());
        proveedor.setMarcasOfrecidas(dto.getMarcasOfrecidas());

        return mapToDTO(proveedorRepository.save(proveedor));
    }

    public ProveedorResponseDTO actualizar(Long id, ProveedorRequestDTO dto) {
        return proveedorRepository.findById(id).map(existente -> {
            existente.setNombreEmpresa(dto.getNombreEmpresa());
            existente.setDatosContacto(dto.getDatosContacto());
            existente.setCondiciones(dto.getCondiciones());
            existente.setMarcasOfrecidas(dto.getMarcasOfrecidas());
            
            return mapToDTO(proveedorRepository.save(existente));
        }).orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Proveedor no encontrado con id: " + id);
        }
        proveedorRepository.deleteById(id);
    }

    public List<ProveedorResponseDTO> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNombreEmpresaContainingIgnoreCase(nombre)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
