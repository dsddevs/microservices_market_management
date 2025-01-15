package dsd.microservices.order_service.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank(message = "Order number is required")
        String orderNumber,
        @NotBlank(message = "SkuCode is required")
        String skuCode,
        @NotBlank(message = "Price is required")
        @Positive
        BigDecimal price,
        @NotBlank(message = "Quantity is required")
        @Positive
        Integer quantity
) { }
