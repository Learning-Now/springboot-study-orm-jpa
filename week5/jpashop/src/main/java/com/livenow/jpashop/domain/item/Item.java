package com.livenow.jpashop.domain.item;

import com.livenow.jpashop.domain.OrderItem;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;


    //비즈니스 로직
    public void addStock(int quantity) {
        stockQuantity = stockQuantity + quantity;
    }

    public void removeStock(int quantity) {
        if (stockQuantity < quantity) {
            throw new ArrayIndexOutOfBoundsException("재고 부족");
        }
        stockQuantity = stockQuantity - quantity;
    }

}