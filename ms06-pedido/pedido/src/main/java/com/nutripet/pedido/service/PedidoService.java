package com.nutripet.pedido.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutripet.pedido.dto.DetalleRequestDTO;
import com.nutripet.pedido.dto.DetalleResponseDTO;
import com.nutripet.pedido.dto.PedidoRequestDTO;
import com.nutripet.pedido.dto.PedidoResponseDTO;
import com.nutripet.pedido.model.Detalle;
import com.nutripet.pedido.model.Estado;
import com.nutripet.pedido.model.Pedido;
import com.nutripet.pedido.repository.PedidoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private DetalleResponseDTO mapDetalleToDTO(Detalle detalle) {
        return new DetalleResponseDTO(
                detalle.getIdProducto(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }

    private PedidoResponseDTO mapPedidoToDTO(Pedido pedido) {
        List<DetalleResponseDTO> detallesDTO = pedido.getDetalles().stream()
                .map(this::mapDetalleToDTO)
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getIdCliente(),
                pedido.getFechaCreacion(),
                pedido.getTotalCobrar(),
                pedido.getEstado(),
                detallesDTO
        );
    }

    @Transactional
    public PedidoResponseDTO crearPedido(PedidoRequestDTO request) {
        Pedido pedido = new Pedido();
        pedido.setIdCliente(request.getIdCliente());
        pedido.setIdDireccionEnvio(request.getIdDireccionEnvio());
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setDetalles(new ArrayList<>());

        BigDecimal totalPedido = BigDecimal.ZERO;

        for (DetalleRequestDTO item : request.getProductos()) {
            BigDecimal subtotal = item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad()));
            
            Detalle detalle = new Detalle(
                    null, 
                    pedido, 
                    item.getIdProducto(), 
                    item.getCantidad(), 
                    item.getPrecioUnitario(), 
                    subtotal
            );
            
            pedido.getDetalles().add(detalle);
            totalPedido = totalPedido.add(subtotal);
        }

        pedido.setTotalCobrar(totalPedido);

        return mapPedidoToDTO(pedidoRepository.save(pedido));
    }

    public List<PedidoResponseDTO> buscarPorProducto(Long idProducto) {
        return pedidoRepository.findPedidosPorProducto(idProducto)
                .stream()
                .map(this::mapPedidoToDTO)
                .collect(Collectors.toList());
    }

}
