package com.nutripet.entrega.config;

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
                                           .title("NutriPet Micro-servicio 9: Entrega")
                                           .version("1.0")
                                           .description("Validar que un pedido no pueda marcarse como \"En Camino\" si no ha sido pagado previamente consultando al MS-07 (Pagos)."));

    }




}
