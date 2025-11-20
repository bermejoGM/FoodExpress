package es.daw.foodexpressapi.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

// ! 1º SE CREA EL ENTITY
@Entity
@Table(name = "dishes")
@Getter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private BigDecimal price;

    private String category;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable = false) // clave foránea restaurant_id
    private Restaurant restaurant; // ? Tengo que poner restaurant_id ???
}