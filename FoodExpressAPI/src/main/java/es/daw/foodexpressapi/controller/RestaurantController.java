package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    // Se inyecta el servicio para la logica de negocio (CRUD --> Crear, Leer, Actualizar, Borrar, Buscar por ... )
    private final RestaurantService restaurantService;

    // Obtener todos los restaurantes
    @GetMapping
    // ResponseEntity envuelve el objeto de respuesta con HTTP status, headers y codigos de estado como 200, 201, 404, etc.
    public ResponseEntity<List<RestaurantDTO>> findAll() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Solo los usuarios con rol ADMIN pueden crear restaurantes
    // @RequestBody indica que el parametro restaurantDTO se obtiene del cuerpo de la solicitud HTTP
    public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO restaurantDTO) {
        // Usamos el Optional porque la creacion puede fallar (nombre duplicado, datos invalidos, etc.) y devolver null no es buena practica
        Optional<RestaurantDTO> result = restaurantService.create(restaurantDTO);

        if (result.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PreAuthorize("hasRole('ADMIN')") // Solo los usuarios con rol ADMIN pueden actualizar restaurantes
    @PutMapping("/{id}") // El id del restaurante a actualizar se pasa como parte de la URL
    // @PathVariable enlaza el parametro id con la variable de la URL
    public ResponseEntity<RestaurantDTO> update(@PathVariable Long id, @RequestBody RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok(restaurantService.update(id, restaurantDTO));
    }

    @PreAuthorize("hasRole('ADMIN')") // Solo los usuarios con rol ADMIN pueden borrar restaurantes
    @DeleteMapping("/{id}") // El id del restaurante a borrar se pasa como parte de la URL
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (restaurantService.delete(id)) {
            return ResponseEntity.noContent().build();  // 204 NO CONTENT
        }

        // Antes estaba con un else aunque debe funcionar exactamente igual
        return ResponseEntity.notFound().build();   // 404 NOT FOUND
    }
}