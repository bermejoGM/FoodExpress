package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// ! 5º CREA EL CONTROLLER
@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping // Metodo GET para obtener todos los platos (no necesita autenticacion, definido en SecurityConfig)
    public ResponseEntity<List<DishDTO>> findAll() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }
}