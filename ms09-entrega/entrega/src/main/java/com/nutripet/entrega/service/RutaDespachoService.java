package com.nutripet.entrega.service;

import com.nutripet.entrega.dto.RutaDespachoRequestDTO;
import com.nutripet.entrega.dto.RutaDespachoResponseDTO;
import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.model.RutaDespacho;
import com.nutripet.entrega.repository.ZonaRepository;
import com.nutripet.entrega.repository.RutaDespachoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RutaDespachoService {

    private final RutaDespachoRepository rutaDespachoRepository;
    private final ZonaRepository zonaRepository;

    private RutaDespachoResponseDTO mapToDTO(RutaDespacho ruta) {
        RutaDespachoResponseDTO dto = new RutaDespachoResponseDTO();
        dto.setIdRuta(ruta.getIdRuta());
        dto.setZonaId(ruta.getZonaId());
        dto.setFechaRuta(ruta.getFechaRuta());
        dto.setUsuarioId(ruta.getUsuarioId());
        dto.setEstado(ruta.getEstado());


        String nombreZona = zonaRepository.findById(ruta.getZonaId())
                .map(Zona::getNombre)
                .orElse("Zona No Definida");
        dto.setZonaNombre(nombreZona);

        return dto;
    }
    public List<RutaDespachoResponseDTO> obtenerTodas() {
        return rutaDespachoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<RutaDespachoResponseDTO> obtenerPorId(Long id) {
        return rutaDespachoRepository.findById(id).map(this::mapToDTO);
    }


    public RutaDespachoResponseDTO guardar(RutaDespachoRequestDTO dto) {

        zonaRepository.findById(dto.getZonaId())
                .orElseThrow(() -> new RuntimeException("Zona logística no encontrada con id: " + dto.getZonaId()));


        if (dto.getEstado().equalsIgnoreCase("En Camino")) {
            throw new RuntimeException("Error Logístico: No se puede iniciar el despacho. Existen pedidos pendientes de pago en MS-07.");
        }

        RutaDespacho ruta = new RutaDespacho();
        ruta.setZonaId(dto.getZonaId());
        ruta.setFechaRuta(dto.getFechaRuta());
        ruta.setUsuarioId(dto.getUsuarioId());
        ruta.setEstado(dto.getEstado());

        return mapToDTO(rutaDespachoRepository.save(ruta));
    }

    public Optional<RutaDespachoResponseDTO> actualizar(Long id, RutaDespachoRequestDTO dto) {
        return rutaDespachoRepository.findById(id).map(existente -> {

            zonaRepository.findById(dto.getZonaId())
                    .orElseThrow(() -> new RuntimeException("Zona logística no encontrada con id: " + dto.getZonaId()));

            if (dto.getEstado().equalsIgnoreCase("En Camino")) {
                throw new RuntimeException("Error Logístico: No se puede cambiar el estado a 'En Camino' si los pedidos no registran pago aprobado en MS-07.");
            }

            existente.setZonaId(dto.getZonaId());
            existente.setFechaRuta(dto.getFechaRuta());
            existente.setUsuarioId(dto.getUsuarioId());
            existente.setEstado(dto.getEstado());

            return mapToDTO(rutaDespachoRepository.save(existente));
        });
    }

    public void eliminar(Long id) {
        rutaDespachoRepository.deleteById(id);
    }
}