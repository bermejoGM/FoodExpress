package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.RestaurantDTO;
import es.daw.foodexpressmvc.service.RestaurantsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    // Maneja las peticiones WEB para listar, crear, editar y eliminar Restaurantes usando RestaurantService

    private final RestaurantsService restaurantsService;

    @GetMapping
    public String listRestaurants(Model model) {
        List<RestaurantDTO> restaurants = restaurantsService.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);

        return "restaurants/restaurants";
    }

    // Muestra el formulario para crear un nuevo restaurante
    @GetMapping("/create")
    public String showForm(Model model, Principal principal) {
        model.addAttribute(principal.getName());
        model.addAttribute("restaurant", new RestaurantDTO());
        model.addAttribute("mode", "create");

        return "restaurant-form";
    }

    // Procesa el formulario del @GetMapping("/create") para crear un nuevo restaurante
    @PostMapping("/create")
    public String create(@ModelAttribute("restaurant") RestaurantDTO restaurantDTO, Model model, Principal principal) {
        RestaurantDTO saved = restaurantsService.create(restaurantDTO);
        model.addAttribute(saved);

        // Pendiente: enviar el restaurante salvado..
        return "restaurants/create-success"; //plantilla
    }

    // Procesa la petición para eliminar un restaurante
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        // mostrar la lista de todos los restaurantes
        restaurantsService.delete(id);

        return "redirect:/restaurants"; // endpoint para listar restaurantes despues de borrar uno
    }

    // Muestra el formulario para editar un restaurante existente
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        // FORMA 1:
        // - restaurantsService.getAllRestaurants();
        // - filtro la lista vía java

        // FORMA 2:
        // - Webclient
        RestaurantDTO restDTO = restaurantsService.findById(id);

        model.addAttribute("mode", "update");
        model.addAttribute("restaurant", restDTO);

        return "restaurants/restaurant-form";
    }

    // Procesa el formulario del @GetMapping("/edit/{id}") para actualizar un restaurante existente
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("restaurant") RestaurantDTO restaurantDTO,
                         BindingResult bindingResult,
                         Model model) {
        // BindingResult contiene (si se generan) errores de validacion
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "update");

            return "restaurants/restaurant-form";
        }

        restaurantsService.update(id, restaurantDTO);

        return "redirect:/restaurants"; // patrong PRG - Post Redirect Get
    }
}