package com.nutripet.precio.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nutripet.precio.dto.PrecioRequestDTO;
import com.nutripet.precio.dto.PrecioResponseDTO;
import com.nutripet.precio.model.Descuento;
import com.nutripet.precio.model.Precio;
import com.nutripet.precio.repository.DescuentoRepository;
import com.nutripet.precio.repository.PrecioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrecioService {

    private final PrecioRepository precioRepository;
    private final DescuentoRepository descuentoRepository;

    private PrecioResponseDTO mapPrecioToDTO(Long idProducto, String tipoCliente, BigDecimal precioBase, BigDecimal montoDescuento, BigDecimal precioFinal) {
        return new PrecioResponseDTO(
                idProducto,
                tipoCliente,
                precioBase,
                montoDescuento,
                precioFinal
        );
    }

    public PrecioResponseDTO calcularPrecioFinal(PrecioRequestDTO request) {
        List<Precio> precios = precioRepository.findByIdProducto(request.getIdProducto());
        
        if (precios.isEmpty()) {
            throw new RuntimeException("Precio no encontrado para el producto ID: " + request.getIdProducto());
        }
        
        Precio pb = precios.get(0);

        List<Descuento> reglas = descuentoRepository.findByTipoCliente(request.getTipoCliente());

        BigDecimal porcentaje = reglas.isEmpty() ? BigDecimal.ZERO : reglas.get(0).getPorcentajeDescuento();
        
        BigDecimal montoDescuento = pb.getPrecioBase().multiply(porcentaje).divide(new BigDecimal("100"));
        BigDecimal precioFinal = pb.getPrecioBase().subtract(montoDescuento);

        return mapPrecioToDTO(
                request.getIdProducto(),
                request.getTipoCliente().name(),
                pb.getPrecioBase(),
                montoDescuento,
                precioFinal
        );
    }

}
