package dsd.microservices.inventory_service.response.data;

import dsd.microservices.inventory_service.response.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuccessResponseData implements SuccessResponse {
    private boolean success;
}
