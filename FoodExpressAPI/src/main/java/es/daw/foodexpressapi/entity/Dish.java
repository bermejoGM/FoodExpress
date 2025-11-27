package es.daw.foodexpressapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
// Representa la tabla dishes de la base de datos
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String category;

    // Si no funciona, cambiar la relacion
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false) // Clave foránea a restaurant
    private Restaurant restaurant;
}