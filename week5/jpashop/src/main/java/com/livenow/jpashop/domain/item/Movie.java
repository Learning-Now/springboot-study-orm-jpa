package com.livenow.jpashop.domain.item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "M")
public class Movie extends Item{

    private String director;
    private String actor;
}
