package com.nutripet.usuario.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutripet.usuario.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    
    List<User> findByNombreContainingIgnoreCase(String nombre);

    List<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.tipoUsuario.id = :tipoId")
    List<User> findByTipoUsuarioId(@Param("tipoId") Long tipoId);

    @Query(
        value = "SELECT * FROM usuarios WHERE email LIKE CONCAT('%', :dominio)",
        nativeQuery = true
    )
    List<User> buscarPorDominioEmail(@Param("dominio") String dominio);

}
