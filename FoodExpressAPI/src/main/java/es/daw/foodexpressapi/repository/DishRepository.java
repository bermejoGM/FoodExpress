package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio JPA para la entidad Dish para usar las operaciones CRUD (se usan en DishService)
public interface DishRepository extends JpaRepository<Dish, Long> {}