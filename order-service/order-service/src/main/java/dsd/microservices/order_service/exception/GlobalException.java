package dsd.microservices.order_service.exception;

import dsd.microservices.order_service.response.MessageResponse;
import dsd.microservices.order_service.response.data.MessageResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        MessageResponseData response = new MessageResponseData();
        response.setSuccess(false);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InventoryStockNotFoundException.class)
    public ResponseEntity<MessageResponse> handleInventoryStockNotFoundException(InventoryStockNotFoundException ex) {
        MessageResponseData response = new MessageResponseData("Inventory stock error - " + ex.getMessage());
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGeneralException(Exception ex) {
        MessageResponseData response = new MessageResponseData("Server error occurred - " + ex.getMessage());
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}