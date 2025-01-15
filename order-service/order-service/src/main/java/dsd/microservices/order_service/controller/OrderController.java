package dsd.microservices.order_service.controller;

import dsd.microservices.order_service.model.service.OrderService;
import dsd.microservices.order_service.model.entity.OrderEntity;
import dsd.microservices.order_service.model.request.OrderRequest;
import dsd.microservices.order_service.response.DataResponse;
import dsd.microservices.order_service.response.MessageResponse;
import dsd.microservices.order_service.util.ServerResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/creation")
    public ResponseEntity<MessageResponse> createOrder(@RequestBody OrderRequest orderRequest){
        orderService.saveOrder(orderRequest);
        String successMessage = "Order successfully created";
        return ServerResponseUtil.createMessageResponse(true, successMessage, HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<DataResponse<List<OrderEntity>>> getOrders(){
        List<OrderEntity> orders = orderService.getAllOrders();
        return ServerResponseUtil.createDataResponse(true, orders, HttpStatus.OK);
    }

}
