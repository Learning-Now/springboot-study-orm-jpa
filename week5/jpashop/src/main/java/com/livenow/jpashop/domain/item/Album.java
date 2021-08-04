package com.livenow.jpashop.domain.item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "A")
public class Album extends Item{

    private String artist;
    private String etc;
}
