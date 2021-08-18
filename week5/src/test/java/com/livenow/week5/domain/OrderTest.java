package com.livenow.week5.domain;

import com.livenow.week5.domain.item.Book;
import com.livenow.week5.domain.item.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void OrderTest() {

        Order order = Order.builder()
                .member(Member.builder().build())
                .build();
        System.out.println(order.getOrderItems());
        order.addOrderItem(OrderItem.createOrderItem(Book.builder()
                                                            .name("asd")
                                                            .stockQuantity(100)
                                                            .build(),1000,10));
        List<OrderItem> orderItemList = order.getOrderItems();
        for (OrderItem orderItem : orderItemList) {
            System.out.println(orderItem.getItem().getName());
        }

    }


}