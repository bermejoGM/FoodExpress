package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.dto.RestaurantDTO;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishesService {
    private final WebClient webClientAPI;
    private final ApiAuthService apiAuthService;

    public List<DishDTO> getAllDishes() {
        DishDTO[] dishes;

        try {
            dishes = webClientAPI
                    .get()
                    .uri("/dishes")
                    .retrieve()
                    .bodyToMono(DishDTO[].class)
                    .block(); //asíncrono
        } catch (Exception e) {
            throw new ConnectionApiRestException(e.getMessage());
        }

        return Arrays.asList(dishes);
    }
}