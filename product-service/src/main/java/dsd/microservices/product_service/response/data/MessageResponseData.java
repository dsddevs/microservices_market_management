package dsd.microservices.product_service.response.data;

import dsd.microservices.product_service.response.MessageResponse;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageResponseData extends SuccessResponseData implements MessageResponse {
    private String message;
}
