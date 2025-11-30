package es.daw.foodexpressmvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    // ! Nombre
    @NotBlank(message = "Dish name is required")
    @Size(min = 2, max = 100, message = "Dish name must be between 2 and 100 characters")
    @Pattern(regexp = "[A-Za-z0-9 -]{2,100}", message = "Dish name contains invalid characters")
    private String name;

    // ! Precio
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @DecimalMax(value = "999.99", message = "Price must not exceed 999.99")
    @Digits(integer = 3, fraction = 2, message = "Price must have max 3 digits and 2 decimals")
    private BigDecimal price;

    // ! Categoría
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    @Pattern(regexp = "[A-Za-z -]{2,50}", message = "Category contains invalid characters")
    private String category;

    // ! Nombre del Restaurante
    @NotBlank(message = "Restaurant is required")
    private String restaurantName;
}