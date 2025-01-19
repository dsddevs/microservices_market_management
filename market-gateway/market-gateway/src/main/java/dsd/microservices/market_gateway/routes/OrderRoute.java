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
public class OrderRoute {

    @Value("${base.urls.order}")
    private String orderBaseUrl;

    @Value("${api.paths.order}")
    private String orderApiPath;

    @Value("${swagger.api.paths.order}")
    private String orderSwaggerApiPath;

    private final RouteUtil routeUtil;

    @Bean
    public RouterFunction<ServerResponse> configureOrderRoute() {
        RouteData orderData =
                new RouteData("order-service", orderBaseUrl, orderApiPath);
        return routeUtil.buildGatewayRouter(orderData);
    }

    @Bean
    public RouterFunction<ServerResponse> configureSwaggerOrderRoute() {
        RouteData orderSwaggerData =
                new RouteData("order-service-swagger", orderBaseUrl, orderSwaggerApiPath);
        return routeUtil.buildSwaggerGatewayRouter(orderSwaggerData);
    }
}
