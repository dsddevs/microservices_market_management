package dsd.microservices.inventory_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String skuCode;
    @Column(nullable = false)
    private Integer quantity;
}
