package dsd.microservices.product_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(initInfo())
                .externalDocs(initExternalDocs())
                .servers(initServer());
    }

    private Info initInfo() {
        return new Info()
                .title("PRODUCT SERVICE API")
                .description("API Documentation for PRODUCT SERVICE")
                .termsOfService("https://giveswagger.com/terms-of-service")
                .version("1.0")
                .license(new License().name("Apache 2.0").url("https://springdoc.org/"));
    }

    private ExternalDocumentation initExternalDocs() {
        return new ExternalDocumentation()
                .description("Additional Documentation for product service")
                .url("https://springdoc.org/");
    }

    private List<Server> initServer() {
        Server newServer = new Server()
                .description("Product Development Server")
                .url("http://localhost:8080");
        return Collections.singletonList(newServer);
    }

}
