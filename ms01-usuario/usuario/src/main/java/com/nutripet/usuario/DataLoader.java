package com.nutripet.usuario;

import java.util.Random;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import com.nutripet.usuario.model.TipoUsuario;
import com.nutripet.usuario.model.User;
import com.nutripet.usuario.repository.TipoUsuarioRepository;
import com.nutripet.usuario.repository.UserRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        if (tipoUsuarioRepository.count() == 0) {
            String[] roles = {"ADMIN", "CLIENTE", "VETERINARIO"};
            for (int i = 0; i < roles.length; i++) {
                TipoUsuario tipo = new TipoUsuario();
                tipo.setId((long) i + 1);
                tipo.setNombre(roles[i]);
                tipoUsuarioRepository.save(tipo);
            }
        }

        List<TipoUsuario> tipos = tipoUsuarioRepository.findAll();

        if (userRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                User user = new User();
                user.setNombre(faker.name().fullName());
                user.setEmail(faker.internet().emailAddress());
                user.setTipoUsuario(tipos.get(random.nextInt(tipos.size())));
                
                userRepository.save(user);
            }
        }
        
        System.out.println(">> DataLoader: Datos iniciales cargados con éxito.");
    }

}
