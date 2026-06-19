package com.nutripet.pago.seeder;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.nutripet.pago.model.Pago;
import com.nutripet.pago.repository.PagoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile("test")
@RequiredArgsConstructor
@Slf4j
public class bdTestSeeder implements CommandLineRunner {

    private final PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si ya existen métodos de pago
        if (pagoRepository.count() == 0) {
            log.info("Iniciando poblamiento de Métodos de Pago en entorno Test...");
            
            // Creamos 3 métodos de pago clásicos
            List<Pago> metodosBase = List.of(
                new Pago(null, "WebPay Plus", "WP-CL-001"),
                new Pago(null, "MercadoPago", "MP-LATAM-002"),
                new Pago(null, "PayPal", "PP-INT-003")
            );
            
            pagoRepository.saveAll(metodosBase);
            log.info("¡Poblamiento de Métodos de Pago completado con éxito!");
        } else {
            log.info("Los métodos de pago ya existen en la base de datos.");
        }
    }
}