package com.nutripet.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.cliente.model.Cliente;
                                        // JpaRepository: METODOS - se hereda todos los metodos basicos de bd (save, findById, findAll, delete)
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
               // findBy           ContainingIgnoreCase: QUERY automatica
    List<Cliente> findByRazonSocialContainingIgnoreCase(String razonSocial);

               // findBy: QUERY automatica
    List<Cliente> findByIdUsuario(Long idUsuario);

    
}
