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
@DiscriminatorValue("A")
@Getter
public class Album extends Item {
    private String artist;
    private String etc;

    @Builder
    public Album(Long id, String name, int price, int stockQuantity, List<Category> categories, String artist, String etc) {
        super(id, name, price, stockQuantity, categories);
        this.artist = artist;
        this.etc = etc;
    }

    protected Album() {

    }
}
