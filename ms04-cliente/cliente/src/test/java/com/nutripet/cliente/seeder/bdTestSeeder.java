package com.nutripet.cliente.seeder;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.nutripet.cliente.model.Cliente;
import com.nutripet.cliente.model.DireccionEnvio;
import com.nutripet.cliente.model.TipoCliente;
import com.nutripet.cliente.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@Component
@Profile("test")
@RequiredArgsConstructor
@Slf4j
public class bdTestSeeder implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            log.info("Iniciando poblamiento de Clientes y Direcciones en entorno Test...");
            seedData();
            log.info("¡Poblamiento de Clientes completado con éxito!");
        }
    }

    private void seedData() {
        Faker faker = new Faker();
        TipoCliente[] tipos = TipoCliente.values();

        // Crear 5 clientes falsos
        for (int i = 0; i < 5; i++) {
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(faker.number().numberBetween(1L, 100L));
            cliente.setTipoCliente(tipos[faker.number().numberBetween(0, tipos.length)]);
            cliente.setRazonSocial(faker.company().name());
            cliente.setTelefono(faker.phoneNumber().cellPhone());
            cliente.setDireccionDespacho(new ArrayList<>());

            // Generar entre 1 y 2 direcciones por cliente
            int cantidadDirecciones = faker.number().numberBetween(1, 3);
            for (int j = 0; j < cantidadDirecciones; j++) {
                DireccionEnvio direccion = new DireccionEnvio();
                direccion.setCliente(cliente); // Vincular con el cliente
                direccion.setCalle(faker.address().streetName());
                direccion.setNumero(faker.address().buildingNumber());
                direccion.setComuna(faker.address().city());
                direccion.setRegion(faker.address().state());
                
                // Agregarlo a la lista del cliente
                cliente.getDireccionDespacho().add(direccion);
            }

            // Al guardar el cliente, el CascadeType.ALL guarda también sus direcciones
            clienteRepository.save(cliente);
        }
    }
}
