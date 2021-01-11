package com.rezdy.lunch.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
@Data
public class Ingredient {

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "best_before")
    private LocalDate bestBefore;

    @Column(name = "use_by")
    private LocalDate useBy;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Recipe> recipies = new HashSet<>();
}
