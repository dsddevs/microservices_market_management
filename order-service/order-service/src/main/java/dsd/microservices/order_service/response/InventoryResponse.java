package dsd.microservices.order_service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dsd.microservices.order_service.response.data.InventoryResponseData;

@JsonDeserialize(as = InventoryResponseData.class)
public interface InventoryResponse {
    boolean isSuccess();
    void setSuccess(boolean success);
}
