package com.nutripet.proveedor.config;

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
                                           .title("NutriPet Micro-servicio 8: Proveedor")
                                           .version("1.0")
                                           .description("Registro de proveedores de alimentos y gestión de órdenes de reposición."));

                                               }




}

