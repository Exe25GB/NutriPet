package com.nutripet.inventario.config;

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
                                           .title("NutriPet Micro-servicio 3: Inventario")
                                           .version("1.0")
                                           .description("Trazabilidad de fechas de vencimiento y alertas de caducidad para evitar pérdidas económicas."));

    }




}
