package dsd.microservices.order_service.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dsd.microservices.order_service.response.InventoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryResponseData implements InventoryResponse {
    private boolean success;
}