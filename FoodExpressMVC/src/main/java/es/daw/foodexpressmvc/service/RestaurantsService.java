package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.RestaurantDTO;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantsService {

    private final WebClient webClientAPI;
    private final ApiAuthService apiAuthService;

    // ! Aqui no se procesa la logica de negocio, se delega a la API

    // Público. Sin jwt
    public List<RestaurantDTO> getAllRestaurants() {
        RestaurantDTO[] restaurants;

        try {
            restaurants = webClientAPI
                    .get()
                    .uri("/restaurants")
                    .retrieve()
                    .bodyToMono(RestaurantDTO[].class)
                    .block(); //asíncrono
        } catch (Exception e) {
            // throw new ConnectionApiRestException("Could not connect to FoodExpress API");
            throw new ConnectionApiRestException(e.getMessage());
        }

        return Arrays.asList(restaurants);
    }

    // Con jwt
    public RestaurantDTO create(RestaurantDTO dto) {
        String token = apiAuthService.getToken();

        RestaurantDTO restaurant;

        try {
            restaurant = webClientAPI
                    .post()
                    .uri("/restaurants")
                    .header("Authorization", "Bearer " + token)
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(RestaurantDTO.class)
                    .block(); //asíncrono
        } catch (Exception e) {
            // Pendiente crear excepción propia
            // Pendiente crear Globla ExceptionHancler: que lea la exceión y redirija a api-error
            //
            //throw new ConnectionApiRestException("Could not connect to FoodExpress API to create restaurant");
            throw new ConnectionApiRestException(e.getMessage());
        }

        return restaurant;
    }

    // Con jwt
    public void delete(Long id) {
        String token = apiAuthService.getToken();

        try {
            webClientAPI
                    .delete()
                    .uri("/restaurants/{id}", id)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block(); //asíncrono
        } catch (Exception e) {
            //throw new ConnectionApiRestException("Could not connect to FoodExpress API to create restaurant");
            throw new ConnectionApiRestException(e.getMessage());
        }
    }

    // Público. Sin jwt
    public RestaurantDTO findById(Long id) {
        // Vía java
        List<RestaurantDTO> dtos = getAllRestaurants();

        return dtos.stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst()
                // Y si no existe!! qué navegación? a qué página?
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado!!!"));
    }

    // Con jwt
    public void update(Long id, RestaurantDTO dto) {
        String token = apiAuthService.getToken();

        try {
            webClientAPI
                    .put()
                    .uri("/restaurants/{id}", id)
                    .header("Authorization", "Bearer " + token)
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(RestaurantDTO.class)
                    .block(); //asíncrono
        } catch (Exception e) {
            //throw new ConnectionApiRestException("Could not connect to FoodExpress API to create restaurant");
            throw new ConnectionApiRestException(e.getMessage());
        }
    }
}