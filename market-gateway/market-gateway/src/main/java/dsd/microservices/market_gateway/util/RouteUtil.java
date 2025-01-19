package dsd.microservices.market_gateway.util;

import dsd.microservices.market_gateway.data.RouteData;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Component
public class RouteUtil {
    public RouterFunction<ServerResponse> buildGatewayRouter(RouteData data) {
        RequestPredicate requestPath = RequestPredicates.path(data.getApiPath());
        HandlerFunction<ServerResponse> baseUrlHandler = HandlerFunctions.http(data.getBaseUrl());
        return GatewayRouterFunctions
                .route(data.getName())
                .route(requestPath, baseUrlHandler)
                .build();
    }

    public RouterFunction<ServerResponse> buildSwaggerGatewayRouter(RouteData data) {
        RequestPredicate requestPath = RequestPredicates.path(data.getApiPath());
        HandlerFunction<ServerResponse> baseUrlHandler = HandlerFunctions.http(data.getBaseUrl());
        return GatewayRouterFunctions
                .route(data.getName())
                .route(requestPath, baseUrlHandler)
                .filter(setPath("/api-docs"))
                .build();
    }


}
