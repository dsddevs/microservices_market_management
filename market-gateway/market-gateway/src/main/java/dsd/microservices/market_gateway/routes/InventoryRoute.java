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
public class InventoryRoute {

    @Value("${base.urls.inventory}")
    private String inventoryBaseUrl;

    @Value("${api.paths.inventory}")
    private String inventoryApiPath;

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions
                .route("inventory_service")
                .route(
                        RequestPredicates.path(inventoryApiPath),
                        HandlerFunctions.http(inventoryBaseUrl))
                .build();
    }


}
