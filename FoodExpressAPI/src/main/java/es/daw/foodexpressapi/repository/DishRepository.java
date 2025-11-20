package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

// ! 2º CREAR EL REPOSITORY
public interface DishRepository extends JpaRepository<Dish, Long> {

}