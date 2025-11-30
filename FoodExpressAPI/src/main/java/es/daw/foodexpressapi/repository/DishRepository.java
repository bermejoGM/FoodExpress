package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Dish;
import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositorio JPA para la entidad Dish para usar las operaciones CRUD (se usan en DishService)
public interface DishRepository extends JpaRepository<Dish, Long> {
    // Metodo personalizado para encontrar platos por el ID del restaurante
    List<Dish> findByRestaurantId(Long id);
    List<Dish> findByRestaurantIdAndCategoryContainingIgnoreCase(Long restaurantId, String category);
}