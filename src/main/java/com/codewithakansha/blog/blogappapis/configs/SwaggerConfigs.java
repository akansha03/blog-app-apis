package com.codewithakansha.blog.blogappapis.configs;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigs {
    @Bean
    public OpenAPI openAPI() {
       return new OpenAPI()
               .info(new Info().title("Blog App API")
                       .description("This is a blogging Application")
                       .version("v.1.0")
                       .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
               )
               .externalDocs(new ExternalDocumentation().description("This is an external documentation")
                       .url("https://google.com"));
    }
}
