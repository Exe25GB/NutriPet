package com.nutripet.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutripet.cliente.main.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{


    List<Cliente> findByRazonSocialContainingIgnoreCase(String razonSocial);

    

    
}
