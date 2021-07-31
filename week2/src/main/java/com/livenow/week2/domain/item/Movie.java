package com.livenow.week2.domain.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("M")
@PrimaryKeyJoinColumn(name = "movie_id")
public class Movie extends Item {

    private String director;
    private String actor;

    public Movie(Long id, String name, int price, String director, String actor) {
        super(id, name, price);
        this.director = director;
        this.actor = actor;
    }
}
