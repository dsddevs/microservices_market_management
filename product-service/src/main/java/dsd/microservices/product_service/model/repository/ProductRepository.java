package dsd.microservices.product_service.model.repository;

import dsd.microservices.product_service.model.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    boolean existsByDescription(String description);
}
