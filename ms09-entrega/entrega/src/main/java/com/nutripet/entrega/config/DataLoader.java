package com.nutripet.entrega.config;

import com.nutripet.entrega.model.RutaDespacho;
import com.nutripet.entrega.model.Zona;
import com.nutripet.entrega.repository.RutaDespachoRepository;
import com.nutripet.entrega.repository.ZonaRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private RutaDespachoRepository rutaDespachoRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        Random random = new Random();

        System.out.println("Iniciando la carga de datos falsos para MS-09 (Entregas)...");

   
        for (int i = 0; i < 6; i++) {
            Zona zona = new Zona();
            
            String nombreZona = faker.address().cityName() + " " + faker.options().option("Norte", "Sur", "Este", "Oeste", "Centro");
            zona.setNombre(nombreZona);
            
            zonaRepository.save(zona);
        }

        List<Zona> zonas = zonaRepository.findAll();


        for (int i = 0; i < 15; i++) {
            RutaDespacho ruta = new RutaDespacho();
            
            Zona zonaAleatoria = zonas.get(random.nextInt(zonas.size()));
            ruta.setZonaId(zonaAleatoria.getIdZona());
            
            ruta.setFechaRuta(LocalDate.now().plusDays(faker.number().numberBetween(0, 7)));
            
            ruta.setUsuarioId((long) faker.number().numberBetween(1, 10));
            
            ruta.setEstado(faker.options().option("Programada", "Pendiente de Carga", "Completada", "Cancelada"));

            rutaDespachoRepository.save(ruta);
        }

        System.out.println("¡Carga de datos finalizada! Se insertaron 6 zonas y 15 rutas de despacho.");
    }
}