package es.daw.foodexpressapi.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

// ! 3º CREA EL DTO
@Data
@Builder
public class DishDTO {
    private String name;
    private BigDecimal price;
    private String category;
    private String restaurantName;
}