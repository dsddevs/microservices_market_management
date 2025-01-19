package dsd.microservices.inventory_service.controller;

import dsd.microservices.inventory_service.model.service.InventoryService;
import dsd.microservices.inventory_service.response.SuccessResponse;
import dsd.microservices.inventory_service.util.ServerResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping
    @Operation(summary = "Inventory checker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory successfully checked"),
            @ApiResponse(responseCode = "404", description = "Inventory skuCode or quantity not found"),
            @ApiResponse(responseCode = "500", description = "Server failed")
    }
    )
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
