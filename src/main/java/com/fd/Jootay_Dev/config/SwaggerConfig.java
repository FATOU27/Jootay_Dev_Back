package com.fd.Jootay_Dev.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Interview Ready API")
                        .description(
                                "API de préparation aux entretiens techniques Java — flashcards, progression et simulation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jootay Dev")
                                .url("https://github.com/FATOU27/Jootay_Dev_Back")))
                .tags(List.of(
                        new Tag().name("Questions").description("Gestion des questions Java"),
                        new Tag().name("Progression").description("Suivi de la progression par question"),
                        new Tag().name("Simulation").description("Mode simulation d'entretien")));
    }
}