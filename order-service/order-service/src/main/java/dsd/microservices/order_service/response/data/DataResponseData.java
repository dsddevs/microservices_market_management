package dsd.microservices.order_service.response.data;

import dsd.microservices.order_service.response.DataResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataResponseData<T> extends SuccessResponseData implements DataResponse<T> {
    private T data;
}