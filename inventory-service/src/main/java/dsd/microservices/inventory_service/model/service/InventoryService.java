package dsd.microservices.inventory_service.model.service;

public interface InventoryService {
    boolean isInStock(String skuCode, Integer quantity);
}
