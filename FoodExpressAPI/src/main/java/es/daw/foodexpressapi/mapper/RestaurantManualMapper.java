package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantManualMapper {
    // Transforma de Entity a DTO y viceversa (el mapper no funciona asi que hay que hacerlo manualmente)

    public RestaurantDTO toDTO(Restaurant restaurant){
        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .build();
    }

    public Restaurant toEntity(RestaurantDTO dto){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        return restaurant;
    }
}