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
@DiscriminatorValue("B")        //default는 class 이름으로 들어감
@Getter
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(Long id, String name, int price, int stockQuantity, List<Category> categories, String author, String isbn) {
        super(id, name, price, stockQuantity, categories);
        this.author = author;
        this.isbn = isbn;
    }

    protected Book() {

    }
}
