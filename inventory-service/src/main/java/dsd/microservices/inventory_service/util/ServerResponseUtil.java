package dsd.microservices.inventory_service.util;

import dsd.microservices.inventory_service.response.SuccessResponse;
import dsd.microservices.inventory_service.response.data.SuccessResponseData;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ServerResponseUtil {

    public static ResponseEntity<SuccessResponse> createSuccessResponse(boolean success, HttpStatus status) {
        SuccessResponseData response = new SuccessResponseData();
        response.setSuccess(success);
        return ResponseEntity.status(status).body(response);
    }
}
