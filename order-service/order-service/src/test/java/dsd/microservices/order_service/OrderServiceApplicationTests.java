package dsd.microservices.order_service;

import com.github.tomakehurst.wiremock.client.WireMock;
import dsd.microservices.order_service.model.repository.OrderRepository;
import dsd.microservices.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.Matchers.equalTo;

@Import({TestcontainersConfiguration.class, FeignTestConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

    @Value("${wiremock.server.port}")
    private int wireMockPort;

    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

    @LocalServerPort
    private Integer port;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        WireMock.configureFor("localhost", wireMockPort);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        orderRepository.deleteAll();
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void shouldCrudOrder() {
        String requestBody = """
                {
                    "orderNumber" : "1",
                    "skuCode" : "apple",
                    "price" : 500.0,
                    "quantity" : 15
                }
                """;
        InventoryClientStub.stubInventoryCall("apple", 15);
        RestAssured.given()
                .contentType("application/json").body(requestBody)
                .when().post("/api/order")
                .then().statusCode(201)
                .body("success", equalTo(true))
                .body("message", equalTo("Order successfully created"));

        RestAssured.given()
                .when().get("/api/order")
                .then().statusCode(200)
                .body("success", equalTo(true))
                .body("data.find { it.orderNumber == '1' }.skuCode", equalTo("apple"))
                .body("data.find { it.orderNumber == '1' }.price", equalTo(500.0f))
                .body("data.find { it.orderNumber == '1' }.quantity", equalTo(15));
    }

}
