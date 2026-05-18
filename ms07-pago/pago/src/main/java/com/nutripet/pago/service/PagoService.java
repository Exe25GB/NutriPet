package com.nutripet.pago.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nutripet.pago.dto.ComprobanteDTO;
import com.nutripet.pago.dto.PagoRequestDTO;
import com.nutripet.pago.dto.PagoResponseDTO;
import com.nutripet.pago.model.Comprobante;
import com.nutripet.pago.model.Estado;
import com.nutripet.pago.model.Pago;
import com.nutripet.pago.model.Transaccion;
import com.nutripet.pago.repository.ComprobanteRepository;
import com.nutripet.pago.repository.PagoRepository;
import com.nutripet.pago.repository.TransaccionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final TransaccionRepository transaccionRepository;
    private final PagoRepository pagoRepository;
    private final ComprobanteRepository comprobanteRepository;

    private ComprobanteDTO mapComprobanteToDTO(Comprobante comprobante) {
        return new ComprobanteDTO(
                comprobante.getTipoDocumento().name(),
                comprobante.getFolioSii(),
                comprobante.getUrlDocumento()
        );
    }

    private PagoResponseDTO mapTransaccionToDTO(Transaccion transaccion, Comprobante comprobante) {
        return new PagoResponseDTO(
                transaccion.getIdTransaccion(),
                transaccion.getIdPedido(),
                transaccion.getEstadoAprobacion(),
                transaccion.getMontoTotal(),
                transaccion.getFechaTransaccion(),
                mapComprobanteToDTO(comprobante)
        );
    }

    @Transactional
    public PagoResponseDTO procesarPago(PagoRequestDTO request) {
        
        Pago metodo = pagoRepository.findById(request.getIdMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado ID: " + request.getIdMetodoPago()));

        Transaccion transaccion = new Transaccion();
        transaccion.setIdPedido(request.getIdPedido());
        transaccion.setMontoTotal(request.getMontoTotal());
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setEstadoAprobacion(Estado.APROBADO);
        transaccion.setMetodoPago(metodo);
        
        Transaccion transaccionGuardada = transaccionRepository.save(transaccion);

        Comprobante comprobante = new Comprobante();
        comprobante.setTransaccion(transaccionGuardada);
        comprobante.setTipoDocumento(request.getTipoDocumento());
        
        String folioSimulado = "SII-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        comprobante.setFolioSii(folioSimulado);
        comprobante.setUrlDocumento("https://nutripet.cl/comprobantes/" + folioSimulado + ".pdf");
        
        Comprobante comprobanteGuardado = comprobanteRepository.save(comprobante);

        return mapTransaccionToDTO(transaccionGuardada, comprobanteGuardado);
    }

}
