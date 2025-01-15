package dsd.microservices.order_service.client;

import dsd.microservices.order_service.response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "${inventory.baseUrl}")
public interface InventoryClient {
    @GetMapping("/api/inventory")
    InventoryResponse isInStock(
            @RequestParam("skuCode") String skuCode,
            @RequestParam("quantity") Integer quantity
    );
}