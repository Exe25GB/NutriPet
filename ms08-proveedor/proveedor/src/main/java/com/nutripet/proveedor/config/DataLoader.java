package com.nutripet.proveedor.config;

import com.nutripet.proveedor.model.OrdenCompra;
import com.nutripet.proveedor.model.Proveedor;
import com.nutripet.proveedor.repository.OrdenCompraRepository;
import com.nutripet.proveedor.repository.ProveedorRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        System.out.println("Iniciando la carga de datos falsos para MS-08 (Proveedores)...");

        for (int i = 0; i < 10; i++) {
            Proveedor proveedor = new Proveedor();
            
            proveedor.setNombreEmpresa(faker.company().name());
            
            String contacto = "Email: " + faker.internet().emailAddress() + " | Tel: " + faker.phoneNumber().phoneNumber();
            proveedor.setDatosContacto(contacto);
            
            proveedor.setCondiciones(faker.options().option("Pago a 30 días", "Pago al contado", "Crédito a 60 días", "Anticipo del 50%"));
            
            String marcas = faker.commerce().brand() + ", " + faker.commerce().brand();
            proveedor.setMarcasOfrecidas(marcas);

            proveedorRepository.save(proveedor);
        }

        List<Proveedor> proveedores = proveedorRepository.findAll();

        for (int i = 0; i < 20; i++) {
            OrdenCompra orden = new OrdenCompra();
            
            Proveedor proveedorAleatorio = proveedores.get(random.nextInt(proveedores.size()));
            orden.setProveedorId(proveedorAleatorio.getIdProveedor());
            
            orden.setEstado(faker.options().option("Generada", "Aprobada", "En Tránsito", "Recibida", "Cancelada"));

            ordenCompraRepository.save(orden);
        }

        System.out.println("¡Carga de datos finalizada! Se insertaron 10 proveedores y 20 órdenes de compra.");
    }
}
