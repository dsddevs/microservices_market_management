package dsd.microservices.inventory_service.controller;

import dsd.microservices.inventory_service.model.service.InventoryService;
import dsd.microservices.inventory_service.response.SuccessResponse;
import dsd.microservices.inventory_service.util.ServerResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    public ResponseEntity<SuccessResponse> getInventoryResult(
            @RequestParam("skuCode") String skuCode,
            @RequestParam("quantity") Integer quantity
    ) {
        boolean isSuccess = inventoryService.isInStock(skuCode, quantity);
        if (isSuccess) {
            return ServerResponseUtil.createSuccessResponse(true, HttpStatus.OK);
        } else {
            return ServerResponseUtil.createSuccessResponse(false, HttpStatus.NOT_FOUND
            );
        }
    }
}
