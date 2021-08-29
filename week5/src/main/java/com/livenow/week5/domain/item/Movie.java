package com.livenow.week5.domain.item;

import com.livenow.week5.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("M")
@Getter
public class Movie extends Item {
    private String director;
    private String actor;

    @Builder
    public Movie(Long id, String name, int price, int stockQuantity, List<Category> categories, String director, String actor) {
        super(id, name, price, stockQuantity, categories);
        this.director = director;
        this.actor = actor;
    }

    public Movie() {

    }
}
