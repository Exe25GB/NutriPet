package com.nutripet.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.usuario.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>  {

}
