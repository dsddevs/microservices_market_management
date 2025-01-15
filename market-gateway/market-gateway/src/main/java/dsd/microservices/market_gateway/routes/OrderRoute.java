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
public class OrderRoute {

    @Value("${base.urls.order}")
    private String orderBaseUrl;

    @Value("${api.paths.order}")
    private String orderApiPath;

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions
                .route("order_service")
                .route(
                        RequestPredicates.path(orderApiPath),
                        HandlerFunctions.http(orderBaseUrl))
                .build();
    }
}
