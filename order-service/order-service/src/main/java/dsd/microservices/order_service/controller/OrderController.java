package dsd.microservices.order_service.controller;

import dsd.microservices.order_service.model.entity.OrderEntity;
import dsd.microservices.order_service.model.request.OrderRequest;
import dsd.microservices.order_service.model.service.OrderService;
import dsd.microservices.order_service.response.DataResponse;
import dsd.microservices.order_service.response.MessageResponse;
import dsd.microservices.order_service.util.ServerResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping()
    @Operation(summary = "Create Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input order data"),
            @ApiResponse(responseCode = "409", description = "Order already exists")
    })
    public ResponseEntity<MessageResponse> createOrder(
            @io.swagger.v3.oas.annotations.parameters
                    .RequestBody(description = "Order details", required = true)
            @RequestBody OrderRequest orderRequest
    ) {
        orderService.saveOrder(orderRequest);
        String successMessage = "Order successfully created";
        return ServerResponseUtil.createMessageResponse(true, successMessage, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully received"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<DataResponse<List<OrderEntity>>> getOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return ServerResponseUtil.createDataResponse(true, orders, HttpStatus.OK);
    }

}
