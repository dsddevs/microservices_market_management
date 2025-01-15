package dsd.microservices.order_service.response.data;

import dsd.microservices.order_service.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageResponseData extends SuccessResponseData implements MessageResponse {
    private String message;
}
