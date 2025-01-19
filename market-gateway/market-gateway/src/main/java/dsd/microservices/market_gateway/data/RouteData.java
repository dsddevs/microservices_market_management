package dsd.microservices.market_gateway.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteData {
    private String name;
    private String baseUrl;
    private String apiPath;
}
