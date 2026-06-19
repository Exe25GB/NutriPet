package com.nutripet.producto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                            .info(new Info()
                                           .title("NutriPet Micro-servicio 2: Producto")
                                           .version("1.0")
                                           .description("Gestión de atributos de productos (marca, tipo de mascota, ciclo vital) y categorías."));

    }




}
