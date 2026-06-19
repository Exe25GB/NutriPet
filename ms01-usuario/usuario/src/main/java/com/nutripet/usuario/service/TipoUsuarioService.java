package com.nutripet.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nutripet.usuario.model.TipoUsuario;
import com.nutripet.usuario.repository.TipoUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoUsuarioService {


    private final TipoUsuarioRepository tipoUsuarioRepository;

    public List<TipoUsuario> obtenerTodas() {
        return tipoUsuarioRepository.findAll();
    }

    public Optional<TipoUsuario> obtenerPorId(Long id) {
        return tipoUsuarioRepository.findById(id);
    }

    // INSERT o UPDATE según si tiene id o no
    public TipoUsuario guardar(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public void eliminar(Long id) {
        tipoUsuarioRepository.deleteById(id);
    }

}
