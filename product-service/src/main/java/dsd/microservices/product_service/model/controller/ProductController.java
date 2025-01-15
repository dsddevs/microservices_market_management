package dsd.microservices.product_service.model.controller;

import dsd.microservices.product_service.model.entity.ProductEntity;
import dsd.microservices.product_service.model.request.ProductRequest;
import dsd.microservices.product_service.model.service.ProductService;
import dsd.microservices.product_service.response.DataResponse;
import dsd.microservices.product_service.response.MessageResponse;
import dsd.microservices.product_service.util.ServerResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<MessageResponse> createProduct(@RequestBody ProductRequest productRequest) {
        productService.saveCreatedProduct(productRequest);
        String successMessage = "Product successfully created";
        return ServerResponseUtil.createMessageResponse(true, successMessage, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<DataResponse<List<ProductEntity>>> getProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return ServerResponseUtil.createDataResponse(true, products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        String successMessage = "Product successfully deleted";
        return ServerResponseUtil.createMessageResponse(true,  successMessage,  HttpStatus.OK);
    }

}
