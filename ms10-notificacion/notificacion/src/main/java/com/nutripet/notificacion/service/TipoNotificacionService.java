package com.nutripet.notificacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nutripet.notificacion.model.TipoNotificacion;
import com.nutripet.notificacion.repository.TipoNotificacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoNotificacionService {

    private final TipoNotificacionRepository tipoNotificacionRepository;

    public List<TipoNotificacion> obtenerTodas() {
        return tipoNotificacionRepository.findAll();
    }

    public Optional<TipoNotificacion> obtenerPorId(Long id) {
        return tipoNotificacionRepository.findById(id);
    }

    public TipoNotificacion guardar(TipoNotificacion tipoNotificacion) {
        return tipoNotificacionRepository.save(tipoNotificacion);
    }

    public void eliminar(Long id) {
        tipoNotificacionRepository.deleteById(id);
    }

}
