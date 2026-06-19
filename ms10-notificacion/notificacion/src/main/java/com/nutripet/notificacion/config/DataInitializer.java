package com.nutripet.notificacion.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nutripet.notificacion.model.Notificacion;
import com.nutripet.notificacion.model.TipoNotificacion;
import com.nutripet.notificacion.repository.NotificacionRepository;
import com.nutripet.notificacion.repository.TipoNotificacionRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner commandLineRunner(
            NotificacionRepository notificacionRepository,
            TipoNotificacionRepository tipoNotificacionRepository) {

        return args -> {
            if (tipoNotificacionRepository.count() == 0) {
                TipoNotificacion email = new TipoNotificacion(null, "EMAIL", "Notificaciones vía correo electrónico");
                TipoNotificacion sms = new TipoNotificacion(null, "SMS", "Mensajes de texto al celular");
                TipoNotificacion push = new TipoNotificacion(null, "PUSH", "Alertas en la aplicación móvil");

                tipoNotificacionRepository.saveAll(List.of(email, sms, push));
                System.out.println(">> DB: Tipos de notificación inicializados.");
            }

            if (notificacionRepository.count() == 0) {
                TipoNotificacion tipoEmail = tipoNotificacionRepository.findAll().get(0);
                TipoNotificacion tipoSms = tipoNotificacionRepository.findAll().get(1);

                Notificacion noti1 = new Notificacion(
                        null,
                        "admin@petfood.com",
                        "Bienvenido al sistema de gestión PetFood",
                        LocalDateTime.now(),
                        tipoEmail
                );

                Notificacion noti2 = new Notificacion(
                        null,
                        "555-0123",
                        "Tu pedido de comida para gato ha sido enviado",
                        LocalDateTime.now(),
                        tipoSms
                );

                notificacionRepository.saveAll(List.of(noti1, noti2));
                System.out.println(">> DB: Notificaciones de prueba cargadas correctamente.");
            }
        };
    }

}
