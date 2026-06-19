package com.nutripet.pedido.seeder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.nutripet.pedido.model.Detalle;
import com.nutripet.pedido.model.Estado;
import com.nutripet.pedido.model.Pedido;
import com.nutripet.pedido.repository.PedidoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@Component
@Profile("test")
@RequiredArgsConstructor
@Slf4j
public class bdTestSeeder implements CommandLineRunner {

    private final PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (pedidoRepository.count() == 0) {
            log.info("Iniciando poblamiento de Pedidos en entorno de Test...");
            seedData();
            log.info("¡Poblamiento de Pedidos completado!");
        }
    }

    private void seedData() {
        Faker faker = new Faker();

        // Crear 5 pedidos de prueba
        for (int i = 0; i < 5; i++) {
            Pedido pedido = new Pedido();
            pedido.setIdCliente(faker.number().numberBetween(1L, 20L));
            pedido.setIdDireccionEnvio(faker.number().numberBetween(1L, 10L));
            pedido.setFechaCreacion(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));
            
            // Seleccionar estado aleatorio
            Estado[] estados = Estado.values();
            pedido.setEstado(estados[faker.number().numberBetween(0, estados.length)]);
            
            pedido.setDetalles(new ArrayList<>());
            BigDecimal totalPedido = BigDecimal.ZERO;

            // Generar entre 1 y 3 detalles (productos) por pedido
            int cantidadDetalles = faker.number().numberBetween(1, 4);
            for (int j = 0; j < cantidadDetalles; j++) {
                Detalle detalle = new Detalle();
                detalle.setPedido(pedido);
                detalle.setIdProducto(faker.number().numberBetween(1L, 15L));
                detalle.setCantidad(faker.number().numberBetween(1, 5));
                
                double precioAleatorio = faker.number().randomDouble(2, 5000, 25000);
                detalle.setPrecioUnitario(BigDecimal.valueOf(precioAleatorio));
                
                // Calcular subtotal
                BigDecimal subtotal = detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad()));
                detalle.setSubtotal(subtotal);
                
                pedido.getDetalles().add(detalle);
                totalPedido = totalPedido.add(subtotal);
            }

            pedido.setTotalCobrar(totalPedido);
            pedidoRepository.save(pedido);
        }
    }
}