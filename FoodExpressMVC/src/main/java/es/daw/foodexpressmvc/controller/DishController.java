package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.service.DishesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DishController {
    private final DishesService dishService;

    @GetMapping
    public String listDishes(Model model, Principal principal) { // ! <<--- HAY QUE IMPORTAR LA INTERFAZ DE SPRINGFRAMEWORK.ORG
        List<DishDTO> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        model.addAttribute(principal.getName());
        return "dishes/dishes"; // plantilla para mostrar los platos
    }
}