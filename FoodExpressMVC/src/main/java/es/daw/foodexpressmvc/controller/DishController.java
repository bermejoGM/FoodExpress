package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.service.DishesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DishController {
    private final DishesService dishesService;

    @GetMapping("/dishes")
    public String listDishes(Model model, Principal principal) {
        List<DishDTO> dishes = dishesService.getAllDishes();
        model.addAttribute("dishes", dishes);
        model.addAttribute("username", principal.getName());

        return "dishes";
    }
}