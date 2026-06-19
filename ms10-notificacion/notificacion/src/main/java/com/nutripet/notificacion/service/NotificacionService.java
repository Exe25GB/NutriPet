package com.nutripet.notificacion.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutripet.notificacion.DTO.NotificacionRequestDTO;
import com.nutripet.notificacion.DTO.NotificacionResponseDTO;
import com.nutripet.notificacion.model.Notificacion;
import com.nutripet.notificacion.model.TipoNotificacion;
import com.nutripet.notificacion.repository.NotificacionRepository;
import com.nutripet.notificacion.repository.TipoNotificacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

  
    private final TipoNotificacionRepository tipoNotificacionRepository;

    private NotificacionResponseDTO mapToDTO(Notificacion notificacion) {
        return new NotificacionResponseDTO(
                notificacion.getId(),
                notificacion.getDestinatario(),
                notificacion.getMensaje(),
                notificacion.getFechaEnvio(),
                notificacion.getTipoNotificacion().getNombre()
        );
    }


    public List<NotificacionResponseDTO> obtenerTodas() {
        return notificacionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<NotificacionResponseDTO> obtenerPorId(Long id) {
        return notificacionRepository.findById(id).map(this::mapToDTO);
    }


    public NotificacionResponseDTO guardar(NotificacionRequestDTO dto) {
        TipoNotificacion tipo = tipoNotificacionRepository
                .findById(dto.getTipoNotificacionId())
                .orElseThrow(() -> new RuntimeException(
                        "Tipo de notificación no encontrado con id: " + dto.getTipoNotificacionId()));

        Notificacion notificacion = new Notificacion();
        notificacion.setDestinatario(dto.getDestinatario());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setFechaEnvio(LocalDateTime.now()); 
        notificacion.setTipoNotificacion(tipo);

        return mapToDTO(notificacionRepository.save(notificacion));
    }

    public Optional<NotificacionResponseDTO> actualizar(Long id, NotificacionRequestDTO dto) {
        return notificacionRepository.findById(id).map(existente -> {
            TipoNotificacion tipo = tipoNotificacionRepository
                    .findById(dto.getTipoNotificacionId())
                    .orElseThrow(() -> new RuntimeException(
                            "Tipo de notificación no encontrado con id: " + dto.getTipoNotificacionId()));

            existente.setDestinatario(dto.getDestinatario());
            existente.setMensaje(dto.getMensaje());
            existente.setTipoNotificacion(tipo);

            return mapToDTO(notificacionRepository.save(existente));
        });
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }

    public List<NotificacionResponseDTO> buscarPorDestinatario(String texto) {
        return notificacionRepository.findByDestinatarioContainingIgnoreCase(texto)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<NotificacionResponseDTO> buscarPorTipo(Long tipoId) {
        return notificacionRepository.findByTipoNotificacionId(tipoId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
