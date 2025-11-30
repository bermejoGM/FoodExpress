package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repositorio JPA para la entidad Restaurant para usar las operaciones CRUD (se usan en RestaurantService)
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByNameContainingIgnoreCase (String name);
}