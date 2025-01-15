package dsd.microservices.market_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class ProductRoute {

    @Value("${base.urls.product}")
    private String productBaseUrl;

    @Value("${api.paths.product}")
    private String productApiPath;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions
                .route("product_service")
                .route(
                        RequestPredicates.path(productApiPath),
                        HandlerFunctions.http(productBaseUrl))
                .build();
    }
}
