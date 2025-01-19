package dsd.microservices.inventory_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "doc")
public class OpenApiConfig {

    @Bean
    public OpenAPI configureOpenApi() {
        return new OpenAPI()
                .info(initInfo())
                .externalDocs(initExternalDocs())
                .servers(initServer());
    }

    private Info initInfo() {
        return new Info()
                .title("Inventory Service API")
                .description("API Documentation for Inventory Service")
                .termsOfService("https://giveswagger.com/terms-of-service")
                .version("v1")
                .license(new License().name("Apache 2.0").url("https://springdoc.org/"));
    }

    private ExternalDocumentation initExternalDocs() {
        return new ExternalDocumentation()
                .description("Additional Documentation for Inventory Service")
                .url("https://springdoc.org/");
    }

    private List<Server> initServer() {
        Server newServer = new Server()
                .description("Inventory Development Server")
                .url("http://localhost:8082");
        return Collections.singletonList(newServer);
    }
}