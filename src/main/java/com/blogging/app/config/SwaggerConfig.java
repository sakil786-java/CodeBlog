package com.blogging.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
                .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Blogging Application", "This Project is developed by Sakil", "1.0", "Terms of service", new Contact("Sakil LAskar", "https://www.linkedin.com/in/sakil-ahammad-laskar-5844361b2/", "sakil@gmail.com"), "License of APIs", "API Licenese URL");

    }
}