package dsd.microservices.order_service.exception;

public class InventoryStockNotFoundException extends RuntimeException{
    public InventoryStockNotFoundException(String message){
        super(message);
    }
}
