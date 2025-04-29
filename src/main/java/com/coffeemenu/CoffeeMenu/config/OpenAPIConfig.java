package com.coffeemenu.CoffeeMenu.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("☕️CoffeeMenu API")
                        .version("1.0.0")
                        .description("A REST API to manage coffee menu items.")
                        .contact(new Contact()
                                .name("Jimmy Dev")
                                .email("jim72@gmail.com")
                                .url("https://github.com/Jimmylawson"))
                );
    }
}
