package dsd.microservices.order_service.client;

import dsd.microservices.order_service.response.InventoryResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(value = "inventory", url = "${inventory.baseUrl}")
//public interface InventoryClient {
//    @GetMapping("/api/inventory")
//    InventoryResponse isInStock(
//            @RequestParam("skuCode") String skuCode,
//            @RequestParam("quantity") Integer quantity
//    );
//}

public interface InventoryClient {
    @GetExchange("/api/inventory")
    InventoryResponse isInStock(
            @RequestParam("skuCode") String skuCode,
            @RequestParam("quantity") Integer quantity
    );
}