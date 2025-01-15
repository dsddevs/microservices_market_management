package dsd.microservices.order_service.util;

import dsd.microservices.order_service.response.DataResponse;
import dsd.microservices.order_service.response.MessageResponse;
import dsd.microservices.order_service.response.data.DataResponseData;
import dsd.microservices.order_service.response.data.MessageResponseData;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ServerResponseUtil {

    public static ResponseEntity<MessageResponse> createMessageResponse(boolean success, String message, HttpStatus status) {
        MessageResponseData response = new MessageResponseData();
        response.setSuccess(success);
        response.setMessage(message);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<DataResponse<T>> createDataResponse(boolean success, T data, HttpStatus status) {
        DataResponseData<T> response = new DataResponseData<>();
        response.setSuccess(success);
        response.setData(data);
        return ResponseEntity.status(status).body(response);
    }
}
