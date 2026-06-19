package com.nutripet.precio.seeder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.nutripet.precio.model.Descuento;
import com.nutripet.precio.model.Precio;
import com.nutripet.precio.model.TipoCliente;
import com.nutripet.precio.repository.DescuentoRepository;
import com.nutripet.precio.repository.PrecioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@Component
@Profile("test")
@RequiredArgsConstructor
@Slf4j
public class bdTestSeeder implements CommandLineRunner {

    private final PrecioRepository precioRepository;
    private final DescuentoRepository descuentoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si la BD ya tiene datos para no duplicar en cada reinicio
        if (precioRepository.count() == 0) {
            log.info("Iniciando el poblamiento de la base de datos con DataFaker...");
            seedData();
            log.info("¡Poblamiento completado con éxito!");
        } else {
            log.info("La base de datos ya contiene datos. Se omite el seeder.");
        }
    }

    private void seedData() {
        Faker faker = new Faker(); // Puedes pasarle una Locale, ej: new Faker(new Locale("es", "CL"))
        List<Long> idsProductos = new ArrayList<>();

        // 1. Crear 15 Precios Base para 15 productos ficticios (ID del 1 al 15)
        for (long i = 1; i <= 15; i++) {
            Precio precio = new Precio();
            precio.setIdProducto(i);
            
            // Genera un precio aleatorio entre 1000 y 50000
            double precioRandom = faker.number().randomDouble(2, 1000, 50000);
            precio.setPrecioBase(BigDecimal.valueOf(precioRandom));
            
            precioRepository.save(precio);
            idsProductos.add(i);
        }

        // 2. Crear reglas de Descuento para algunos de esos productos
        for (int i = 0; i < 10; i++) {
            Descuento descuento = new Descuento();
            
            // Seleccionar un producto al azar de los que acabamos de crear
            Long idProductoRandom = idsProductos.get(faker.number().numberBetween(0, idsProductos.size()));
            descuento.setIdProducto(idProductoRandom);
            
            // Asignar tipo de cliente (50% probabilidad de ser MAYORISTA o MINORISTA)
            descuento.setTipoCliente(faker.bool().bool() ? TipoCliente.MAYORISTA : TipoCliente.MINORISTA);
            
            // Porcentaje de descuento (ej: entre 5% y 30%)
            double porcentaje = faker.number().randomDouble(2, 5, 30);
            descuento.setPorcentajeDescuento(BigDecimal.valueOf(porcentaje));
            
            // Compra mínima (ej: entre 0 y 20000)
            double compraMinima = faker.number().randomDouble(2, 0, 20000);
            descuento.setCompraMinima(BigDecimal.valueOf(compraMinima));

            // Fechas de validez usando TimeUnit de DataFaker
            LocalDateTime fechaInicio = faker.date().past(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fechaFin = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            descuento.setFechaInicio(fechaInicio);
            descuento.setFechaFinal(fechaFin);

            descuentoRepository.save(descuento);
        }
    }

}
