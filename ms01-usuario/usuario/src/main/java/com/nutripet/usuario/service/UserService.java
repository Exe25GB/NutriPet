package com.nutripet.usuario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutripet.usuario.DTO.UserRequestDTO;
import com.nutripet.usuario.DTO.UserResponseDTO;
import com.nutripet.usuario.model.TipoUsuario;
import com.nutripet.usuario.repository.TipoUsuarioRepository;
import com.nutripet.usuario.repository.UserRepository;
import com.nutripet.usuario.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getDireccion(),
                user.getTipoUsuario().getNombre()
        );
    }

    public List<UserResponseDTO> obtenerTodos() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> obtenerPorId(Long id) {
        return userRepository.findById(id).map(this::mapToDTO);
    }

    public UserResponseDTO guardar(UserRequestDTO dto) {
        TipoUsuario tipo = tipoUsuarioRepository
                .findById(dto.getTipoUsuarioId())
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de usuario no encontrado con id: " + dto.getTipoUsuarioId()));

        User user = new User(
                null,
                dto.getNombre(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getDireccion(),
                tipo
        );

        return mapToDTO(userRepository.save(user));
    }

    public Optional<UserResponseDTO> actualizar(Long id, UserRequestDTO dto) {
        return userRepository.findById(id).map(existente -> {
            TipoUsuario tipo = tipoUsuarioRepository
                    .findById(dto.getTipoUsuarioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Tipo de usuario no encontrado con id: " + dto.getTipoUsuarioId()));

            existente.setNombre(dto.getNombre());
            existente.setEmail(dto.getEmail());
            existente.setPassword(dto.getPassword());
            existente.setDireccion(dto.getDireccion());
            existente.setTipoUsuario(tipo);

            return mapToDTO(userRepository.save(existente));
        });
    }

    public void eliminar(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> buscarPorNombre(String texto) {
        return userRepository.findByNombreContainingIgnoreCase(texto)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UserResponseDTO> buscarPorTipo(Long tipoId) {
        return userRepository.findByTipoUsuarioId(tipoId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
