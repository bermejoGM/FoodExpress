package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.entity.Dish;
import es.daw.foodexpressapi.entity.Restaurant;
import es.daw.foodexpressapi.mapper.DishManualMapper;
import es.daw.foodexpressapi.repository.DishRepository;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantService restaurantService; // ? Entonces esto pa que coño se usa
    private final DishManualMapper dishManualMapper;
    private final RestaurantRepository restaurantRepository;

    public List<DishDTO> getAllDishes() {
        return dishRepository.findAll().stream()
                .map(dishManualMapper::toDTO)
                .toList();
    }

    public List<DishDTO> getDishesByRestaurant(Long id){
        return dishRepository.findByRestaurantId(id).stream()
                .map(dishManualMapper::toDTO)
                .toList();
    }

    public List<DishDTO> getDishesByRestaurantAndCategory(Long restaurantId, String category) {
        return dishRepository.findByRestaurantIdAndCategoryContainingIgnoreCase(restaurantId, category).stream()
                .map(dishManualMapper::toDTO)
                .toList();
    }

    public Optional<DishDTO> createDishInRestaurant(Long id, DishDTO dishDTO) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(id);

        if (restaurantOpt.isEmpty()) {  // Si NO existe
            return Optional.empty();
        }

        Dish dish = dishManualMapper.toEntity(dishDTO, restaurantOpt.get());
        Dish newDish = dishRepository.save(dish);

        return Optional.of(dishManualMapper.toDTO(newDish));
    }

}