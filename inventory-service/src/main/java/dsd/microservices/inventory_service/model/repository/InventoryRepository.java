package dsd.microservices.inventory_service.model.repository;

import dsd.microservices.inventory_service.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    boolean existsBySkuCodeAndQuantityEquals(String skuCode, Integer quantity);
}
