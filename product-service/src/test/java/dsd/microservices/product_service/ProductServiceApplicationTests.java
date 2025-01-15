package dsd.microservices.product_service;

import dsd.microservices.product_service.model.repository.ProductRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import static org.hamcrest.Matchers.equalTo;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @LocalServerPort
    private Integer port;

	@Autowired
	private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
		productRepository.deleteAll();
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void shouldCrudProduct() {
        String requestBody = """
                {
                    "name" : "A",
                    "description" : "A is the best product",
                    "price" : 100.0
                }
                """;
        RestAssured.given()
                .contentType("application/json").body(requestBody)
                .when().post("/api/product")
                .then().statusCode(201)
                .body("success", equalTo(true))
                .body("message", equalTo("Product successfully created"));

		RestAssured.given()
				.when().get("/api/product")
				.then().statusCode(200)
				.body("success", equalTo(true))
				.body("data.find { it.name == 'A' }.description", equalTo("A is the best product"))
				.body("data.find { it.name == 'A' }.price", equalTo(100.0f));

		RestAssured.given()
				.when().delete("/api/product/6782e93a34b3cc1bfd1c0bb0")
				.then().statusCode(200)
				.body("success", equalTo(true))
				.body("message", equalTo("Product successfully deleted"));
    }

}
