package com.livenow.week2.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("A")
@PrimaryKeyJoinColumn(name = "album_id")
public class Album extends Item {

    private String artist;

    public Album(Long id, String name, int price, String artist) {
        super(id, name, price);
        this.artist = artist;
    }
}
