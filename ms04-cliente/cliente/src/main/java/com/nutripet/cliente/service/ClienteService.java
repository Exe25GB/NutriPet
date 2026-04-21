package com.nutripet.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nutripet.cliente.main.Cliente;
import com.nutripet.cliente.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> clienteTodos(){
        return repository.findAll();
    }

    public Optional<Cliente> clientePorId(Long idCliente){
        return repository.findById(idCliente);
    }

    public Cliente registrarCliente(Cliente nCliente){
        return repository.save(nCliente);
    }

    public void eliminarClientePorId(Long idCliente){
        repository.deleteById(idCliente);
    }


}
