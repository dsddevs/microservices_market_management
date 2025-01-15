package dsd.microservices.order_service.model.service.in;

import dsd.microservices.order_service.client.InventoryClient;
import dsd.microservices.order_service.exception.InventoryStockNotFoundException;
import dsd.microservices.order_service.model.entity.OrderEntity;
import dsd.microservices.order_service.model.mapper.OrderMapper;
import dsd.microservices.order_service.model.repository.OrderRepository;
import dsd.microservices.order_service.model.request.OrderRequest;
import dsd.microservices.order_service.model.service.OrderService;
import dsd.microservices.order_service.response.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderInService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        OrderEntity order = createOrder(orderRequest);
        orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    private OrderEntity createOrder(OrderRequest orderRequest) {
        InventoryResponse response = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (!response.isSuccess()) {
            throw new InventoryStockNotFoundException("The ordered product is not in stock");
        }
        return orderMapper.toEntity(orderRequest);
    }
}
