package dsd.microservices.inventory_service;

import dsd.microservices.inventory_service.model.entity.InventoryEntity;
import dsd.microservices.inventory_service.model.repository.InventoryRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.Matchers.equalTo;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@Autowired
	private InventoryRepository inventoryRepository;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		inventoryRepository.deleteAll();
		InventoryEntity inventory = new InventoryEntity();
		inventory.setSkuCode("apple");
		inventory.setQuantity(15);
		inventoryRepository.save(inventory);
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldCheckingInventory() {

		RestAssured.given()
				.contentType("application/json")
				.when().get("/api/inventory?skuCode=apple&quantity=15")
				.then().statusCode(200)
				.body("success", equalTo(true));
	}
}
