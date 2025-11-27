package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.entity.Restaurant;
import es.daw.foodexpressapi.mapper.DishManualMapper;
import es.daw.foodexpressapi.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantService restaurantService; // ? Entonces esto pa que coño se usa
    private final DishManualMapper dishManualMapper;

    public List<DishDTO> getAllDishes() {
        return dishRepository.findAll().stream()
                .map(dishManualMapper::toDTO)
                .toList();
    }
}