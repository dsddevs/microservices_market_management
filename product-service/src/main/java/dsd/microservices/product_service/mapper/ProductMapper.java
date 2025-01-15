package dsd.microservices.product_service.mapper;

import dsd.microservices.product_service.model.entity.ProductEntity;
import dsd.microservices.product_service.model.request.ProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toEntity(ProductRequest productRequest);
}
