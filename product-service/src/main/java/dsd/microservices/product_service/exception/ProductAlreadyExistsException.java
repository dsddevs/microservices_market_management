package dsd.microservices.product_service.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
