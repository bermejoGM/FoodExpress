package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.entity.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishManualMapper {
    public DishDTO toDTO(Dish dish) {
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .restaurantName(dish.getRestaurant().getName())
                .build();
    }

    public Dish toEntity(DishDTO dishDTO) {
        Dish dish = new Dish();
        dish.setId(dishDTO.getId());
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        // ! No se establece la relación con Restaurant aqui, no es necesario para el DTO
        return dish;
    }
}