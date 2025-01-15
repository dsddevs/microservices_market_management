package dsd.microservices.product_service.model.service;

import dsd.microservices.product_service.model.entity.ProductEntity;
import dsd.microservices.product_service.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    void saveCreatedProduct(ProductRequest productRequest);
    List<ProductEntity> getAllProducts();
    void deleteProductById(String id);
}
