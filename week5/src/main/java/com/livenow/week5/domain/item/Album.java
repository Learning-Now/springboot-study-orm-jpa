package com.livenow.week5.domain.item;

import com.livenow.week5.domain.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    @Builder
    public Album(Long id, String name, int price, int stockQuantity, String artist, String etc) {
        super(id, name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }
}
