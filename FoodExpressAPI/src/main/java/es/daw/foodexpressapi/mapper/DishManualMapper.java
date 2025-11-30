package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.entity.Dish;
import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class DishManualMapper {
    public DishManualMapper() {
    }

    public DishDTO toDTO(Dish dish) {
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .category(dish.getCategory())
                .price(dish.getPrice())
                .restaurantName(dish.getRestaurant().getName())
                .build();
    }

    public Dish toEntity(DishDTO dishDTO, Restaurant restaurant) {
        Dish dish = new Dish();
        dish.setId(dishDTO.getId());
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        dish.setCategory(dishDTO.getCategory());
        dish.setRestaurant(restaurant);
        return dish;
    }
}