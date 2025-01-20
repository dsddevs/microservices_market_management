package dsd.microservices.order_service.config;

import dsd.microservices.order_service.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${inventory.baseUrl}")
    private String baseUrl;

    @Bean
    public InventoryClient inventoryClient() {
        HttpServiceProxyFactory proxyFactory = buildProxyFactory();
        return proxyFactory.createClient(InventoryClient.class);
    }

    private HttpServiceProxyFactory buildProxyFactory() {
        return HttpServiceProxyFactory
                .builderFor(createRestClientAdapter())
                .build();
    }

    private RestClientAdapter createRestClientAdapter() {
        return RestClientAdapter.create(buildRestClient());
    }

    private RestClient buildRestClient() {
        return RestClient.builder().baseUrl(baseUrl).build();
    }

}
