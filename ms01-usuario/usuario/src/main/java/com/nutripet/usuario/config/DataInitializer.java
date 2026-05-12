package com.nutripet.usuario.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.nutripet.usuario.model.User;
import com.nutripet.usuario.model.TipoUsuario;
import com.nutripet.usuario.repository.TipoUsuarioRepository;
import com.nutripet.usuario.repository.UserRepository;

@Configuration
public class DataInitializer{

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            TipoUsuarioRepository tipoUsuarioRepository) {

        return args -> {
           
            if (tipoUsuarioRepository.count() == 0) {
                TipoUsuario adminTipo = new TipoUsuario(null, "ADMIN", "Gestor total del sistema");
                TipoUsuario clienteTipo = new TipoUsuario(null, "CLIENTE", "Comprador de productos");

                tipoUsuarioRepository.saveAll(List.of(adminTipo, clienteTipo));
                System.out.println(">> DB: Tipos de usuario inicializados.");
            }

           
            if (userRepository.count() == 0) {
                TipoUsuario tipoAdmin = tipoUsuarioRepository.findAll().get(0);
                TipoUsuario tipoCliente = tipoUsuarioRepository.findAll().get(1);

                User admin = new User(
                        null,
                        "Admin PetFood",
                        "admin@petfood.com",
                        "admin123",
                        "Sede Principal",
                        tipoAdmin
                );

                User cliente = new User(
                        null,
                        "Juan Perez",
                        "juan@gmail.com",
                        "juan123",
                        "Calle Falsa 123",
                        tipoCliente
                );

                userRepository.saveAll(List.of(admin, cliente));
                System.out.println(">> DB: Usuarios de prueba cargados correctamente.");
            }
        };
    }

}
