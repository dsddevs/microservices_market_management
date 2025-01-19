package dsd.microservices.market_gateway.routes;

import dsd.microservices.market_gateway.data.RouteData;
import dsd.microservices.market_gateway.util.RouteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class InventoryRoute {

    @Value("${base.urls.inventory}")
    private String inventoryBaseUrl;

    @Value("${api.paths.inventory}")
    private String inventoryApiPath;

    @Value("${swagger.api.paths.inventory}")
    private String inventorySwaggerApiPath;

    private final RouteUtil routeUtil;

    @Bean
    public RouterFunction<ServerResponse> configureInventoryRoute() {
        RouteData inventoryData =
                new RouteData("inventory-service", inventoryBaseUrl, inventoryApiPath);
        return routeUtil.buildGatewayRouter(inventoryData);
    }

    @Bean
    public RouterFunction<ServerResponse> configureSwaggerInventoryRoute() {
        RouteData inventorySwaggerData =
                new RouteData("inventory-service-swagger", inventoryBaseUrl, inventorySwaggerApiPath);
        return routeUtil.buildSwaggerGatewayRouter(inventorySwaggerData);
    }

}
