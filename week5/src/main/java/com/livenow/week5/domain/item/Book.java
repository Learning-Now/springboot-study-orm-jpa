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
@DiscriminatorValue("B")        //default는 class 이름으로 들어감
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        super(id, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
