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
public class ProductRoute {

    @Value("${base.urls.product}")
    private String productBaseUrl;

    @Value("${api.paths.product}")
    private String productApiPath;

    @Value("${swagger.api.paths.product}")
    private String productSwaggerApiPath;

    private final RouteUtil routeUtil;

    @Bean
    public RouterFunction<ServerResponse> configureProductRoute() {
        RouteData productData =
                new RouteData("product-service", productBaseUrl, productApiPath);
        return routeUtil.buildGatewayRouter(productData);
    }

    @Bean
    public RouterFunction<ServerResponse> configureSwaggerProductRoute() {
        RouteData productSwaggerData =
                new RouteData("product-service-swagger", productBaseUrl, productSwaggerApiPath);
        return routeUtil.buildSwaggerGatewayRouter(productSwaggerData);
    }
}


