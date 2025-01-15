package dsd.microservices.order_service.model.mapper;

import dsd.microservices.order_service.model.entity.OrderEntity;
import dsd.microservices.order_service.model.request.OrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toEntity(OrderRequest orderRequest);
}
