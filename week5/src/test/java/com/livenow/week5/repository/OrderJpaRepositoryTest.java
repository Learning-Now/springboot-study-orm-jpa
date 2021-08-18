package com.livenow.week5.repository;

import com.livenow.week5.domain.*;
import com.livenow.week5.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderJpaRepositoryTest {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;


    @Test
    void findOrder() {

        //given
        Member member = Member.builder()
                .name("동현")
                .address(new Address("부평", "안남로","123"))
                .build();
        memberJpaRepository.save(member);
        Order order = Order.createOrder(member, Delivery.builder()
                .status(DeliveryStatus.READY)
                .address(new Address("부평", "안남로","123"))
                .build());
        OrderItem orderItem = OrderItem.createOrderItem(Book.builder()
                .author("김영한")
                .name("JPA")
                .isbn("123")
                .stockQuantity(100)
                .build(), 1000, 10);
        //order.addOrderItem(orderItem);
        orderJpaRepository.save(order);

        //when
        List<Order> orders = orderJpaRepository.findByMember(member);

        //then
        for (Order order1 : orders) {
            System.out.println(order1.getMember().getName());
        }
    }

}