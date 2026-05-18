package com.nutripet.cliente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutripet.cliente.dto.ClienteRequestDTO;
import com.nutripet.cliente.dto.ClienteResponseDTO;
import com.nutripet.cliente.dto.DireccionRequestDTO;
import com.nutripet.cliente.dto.DireccionResponseDTO;
import com.nutripet.cliente.model.Cliente;
import com.nutripet.cliente.model.DireccionEnvio;
import com.nutripet.cliente.repository.ClienteRepository;
import com.nutripet.cliente.repository.DireccionEnvioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    // Repositorios inyectados para poder manipular la base de datos.
    private final ClienteRepository clienteRepository;
    private final DireccionEnvioRepository direccionRepository;

    // MODELO CLIENTE
        // Mapeo Privado: Entidad a ResponseDTO. 
        // Mantiene la arquitectura limpia asegurando que los Controladores nunca vean Modelos de BD, solo DTOs.
 // mapeo de la entidad CLIENTE del DTO
    private ClienteResponseDTO clienteMapToDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getIdUsuario(),
                cliente.getTipoCliente(),
                cliente.getRazonSocial(),
                cliente.getTelefono()
        );
    }

    // CRUD CLIENTES
 // Cread
    public ClienteResponseDTO guardar(ClienteRequestDTO dto) {
        // Creamos la entidad mapeando manualmente los datos del RequestDTO. El ID va null para que BD lo autogenere.
        Cliente cliente = new Cliente(
                null,
                dto.getIdUsuario(),
                dto.getTipoCliente(),
                dto.getRazonSocial(),
                dto.getTelefono(),
                new ArrayList<>() // Lista de direcciones inicialmente vacía
        );
        return clienteMapToDTO(clienteRepository.save(cliente)); // Guarda y devuelve el DTO resultante
    }

 // Read
    // obtener todos los clientes
    public List<ClienteResponseDTO> obtenerTodos() {
        return clienteRepository.findAll().stream().map(this::clienteMapToDTO).collect(Collectors.toList());
    }
    // buscar cliente por id
    public Optional<ClienteResponseDTO> obtenerPorId(Long id) {
        return clienteRepository.findById(id).map(this::clienteMapToDTO);
    }    
        

 // Update
    // actualizar cliente por id
    public Optional<ClienteResponseDTO> actualizar(Long id, ClienteRequestDTO dto) {
        // Buscamos si existe. Si existe, modificamos sus valores con el DTO y lo guardamos encima.
        return clienteRepository.findById(id).map(existente -> {
            existente.setIdUsuario(dto.getIdUsuario());
            existente.setTipoCliente(dto.getTipoCliente());
            existente.setRazonSocial(dto.getRazonSocial());
            existente.setTelefono(dto.getTelefono());
            return clienteMapToDTO(clienteRepository.save(existente));
        });
    }

 // Delete
    // eliminar cliente por id
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    // MODELO DIRECCION_ENVIO
    // Mapeo Privado: Convierte la entidad interna DireccionEnvio al DTO de salida
    // Al ser privado, ninguna otra clase externa puede acceder ni depender de esta lógica de conversión
 // mapeo de la entidad DIRECCION_ENVIO del DTO
    private DireccionResponseDTO mapDireccionToDTO(DireccionEnvio dir) {
        return new DireccionResponseDTO(
                dir.getIdDireccion(),
                dir.getCliente().getIdCliente(),
                dir.getCalle(),
                dir.getNumero(),
                dir.getComuna(),
                dir.getRegion()
        );
    }

    // CRUD DIRECCION_ENVIO
 // Cread
    public DireccionResponseDTO agregarDireccion(Long idCliente, DireccionRequestDTO dto) {
        
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));

        DireccionEnvio direccion = new DireccionEnvio(
                null,
                cliente,
                dto.getCalle(),
                dto.getNumero(),
                dto.getComuna(),
                dto.getRegion()
        );
        return mapDireccionToDTO(direccionRepository.save(direccion));
    }

 // Read
    //  obtener todos las dirreciones
    public List<DireccionResponseDTO> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll() // Busca absolutamente todas las direcciones en la BD
                .stream()
                .map(this::mapDireccionToDTO) // Las convierte al formato de salida DTO
                .collect(Collectors.toList());
    }

    // buscar una dirección de envío específica por su id
    public Optional<DireccionResponseDTO> obtenerDireccionPorId(Long idDireccion) {
        return direccionRepository.findById(idDireccion).map(this::mapDireccionToDTO);
    }

    // obtener todos las direecion de envio por cliente
    public List<DireccionResponseDTO> listarDireccionesPorCliente(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));

        return cliente.getDireccionDespacho().stream().map(this::mapDireccionToDTO).collect(Collectors.toList());
    }

    // 

 // Update
    
 // Delete
    //se borra si borras el cliente

}
