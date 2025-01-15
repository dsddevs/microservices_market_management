package dsd.microservices.inventory_service.model.service.in;

import dsd.microservices.inventory_service.model.repository.InventoryRepository;
import dsd.microservices.inventory_service.model.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryInService implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityEquals(skuCode, quantity);
    }
}
