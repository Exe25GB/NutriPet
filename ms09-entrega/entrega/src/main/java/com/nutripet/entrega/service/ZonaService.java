package com.nutripet.entrega.service;

import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.repository.ZonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZonaService {

    private final ZonaRepository zonaRepository;

    public List<Zona> obtenerTodas() {
        return zonaRepository.findAll();
    }

    public Optional<Zona> obtenerPorId(Long id) {
        return zonaRepository.findById(id);
    }

    public Zona guardar(Zona zona) {
        return zonaRepository.save(zona);
    }

    public void eliminar(Long id) {
        zonaRepository.deleteById(id);
    }
}