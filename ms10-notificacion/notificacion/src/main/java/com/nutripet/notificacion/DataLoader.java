package com.nutripet.notificacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.nutripet.notificacion.model.Notificacion;
import com.nutripet.notificacion.model.TipoNotificacion;
import com.nutripet.notificacion.repository.NotificacionRepository;
import com.nutripet.notificacion.repository.TipoNotificacionRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private NotificacionRepository notificacionRepository;
    
    @Autowired
    private TipoNotificacionRepository tipoNotificacionRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        if (tipoNotificacionRepository.count() == 0) {
            String[] tipos = {"EMAIL", "SMS", "PUSH"};
            for (int i = 0; i < tipos.length; i++) {
                TipoNotificacion tipo = new TipoNotificacion();
                tipo.setId((long) i + 1);
                tipo.setNombre(tipos[i]);
                tipoNotificacionRepository.save(tipo);
            }
        }

        List<TipoNotificacion> listaTipos = tipoNotificacionRepository.findAll();

        if (notificacionRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Notificacion noti = new Notificacion();
                noti.setDestinatario(faker.internet().emailAddress());
                noti.setMensaje(faker.lorem().sentence(10));
                noti.setFechaEnvio(LocalDateTime.now());
                
                noti.setTipoNotificacion(listaTipos.get(random.nextInt(listaTipos.size())));
                
                notificacionRepository.save(noti);
            }
        }
        
        System.out.println(">> DataLoader: Datos de notificaciones cargados con éxito.");
    }

}
