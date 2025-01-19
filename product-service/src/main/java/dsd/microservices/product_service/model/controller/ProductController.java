package dsd.microservices.product_service.model.controller;

import dsd.microservices.product_service.model.entity.ProductEntity;
import dsd.microservices.product_service.model.request.ProductRequest;
import dsd.microservices.product_service.model.service.ProductService;
import dsd.microservices.product_service.response.DataResponse;
import dsd.microservices.product_service.response.MessageResponse;
import dsd.microservices.product_service.util.ServerResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product Management APIs ")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input product data"),
            @ApiResponse(responseCode = "409", description = "Product already exists")
    })
    public ResponseEntity<MessageResponse> createProduct(
            @io.swagger.v3.oas.annotations.parameters
                    .RequestBody(description = "Product details", required = true)
            @RequestBody ProductRequest productRequest
    ) {
        productService.saveCreatedProduct(productRequest);
        String successMessage = "Product successfully created";
        return ServerResponseUtil.createMessageResponse(true, successMessage, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
            @ApiResponse(responseCode = "404", description = "No products found")
    })
    public ResponseEntity<DataResponse<List<ProductEntity>>> getProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return ServerResponseUtil.createDataResponse(true, products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID")
    })
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        String successMessage = "Product successfully deleted";
        return ServerResponseUtil.createMessageResponse(true, successMessage, HttpStatus.OK);
    }

}
