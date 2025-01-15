package dsd.microservices.product_service.model.service;

import dsd.microservices.product_service.mapper.ProductMapper;
import dsd.microservices.product_service.model.entity.ProductEntity;
import dsd.microservices.product_service.model.repository.ProductRepository;
import dsd.microservices.product_service.model.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductInService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void saveCreatedProduct(ProductRequest productRequest) {
        ProductEntity newProduct = productMapper.toEntity(productRequest);
        productRepository.save(newProduct);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(String id){
        Optional<ProductEntity> currentProduct = productRepository.findById(id);
        if (currentProduct.isPresent()) {
            ProductEntity product = currentProduct.get();
            productRepository.delete(product);
        }
    }
}
