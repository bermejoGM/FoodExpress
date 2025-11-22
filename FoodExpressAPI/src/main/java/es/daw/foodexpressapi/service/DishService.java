package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.entity.Dish;
import es.daw.foodexpressapi.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// ! 4º CREA EL SERVICE
@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository; // creado en el repository (paso 2)

    public List<DishDTO> getAllDishes(){
        return dishRepository.findAll().stream() // Para poder usar el findAll tenemos que crear un controller (paso 5)
                .map(this::toDTO)
                .toList();
    }

    public DishDTO toDTO(Dish dish){
        return DishDTO.builder()
                .name(dish.getName())
                .price(dish.getPrice())
                .category(dish.getCategory())
                .restaurantName(dish.getRestaurant().getName())
                .build();
    }
}