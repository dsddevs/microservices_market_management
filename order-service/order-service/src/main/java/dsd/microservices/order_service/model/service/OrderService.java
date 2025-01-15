package dsd.microservices.order_service.model.service;

import dsd.microservices.order_service.model.entity.OrderEntity;
import dsd.microservices.order_service.model.request.OrderRequest;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderRequest orderRequest);
    List<OrderEntity> getAllOrders();
}
