package com.livenow.week5;

import com.livenow.week5.domain.*;
import com.livenow.week5.domain.item.Book;
import com.livenow.week5.repository.ItemRepository;
import com.livenow.week5.repository.MemberRepository;
import com.livenow.week5.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("default|test")
public class DataLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Book book = Book.builder()
                            .author("정윤")
                            .isbn("isbn")
                            .price(10000)
                            .name("동현")
                            .stockQuantity(20)
                            .build();
        itemRepository.save(book);
        Address address = new Address("시", "군", "구");
        Member member = Member.builder()
                                    .address(address)
                                    .name("다훈")
                                    .build();
        memberRepository.save(member);
        OrderItem orderItem = OrderItem.createOrderItem(book, 2, 2);
        Delivery delivery = Delivery.builder()
                                        .address(address)
                                        .status(DeliveryStatus.COMP)
                                        .build();
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .build();
        orderRepository.save(order);
    }
}
