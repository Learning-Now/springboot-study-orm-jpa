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
                .name("동현이는 바보야")
                .author("정윤")
                .isbn("isbn")
                .price(10000)
                .stockQuantity(20)
                .build();
        itemRepository.save(book);

        Address address = Address.builder()
                .city("인천")
                .street("연수구")
                .zipcode("아카데미로")
                .build();
        Member member = Member.builder()
                .address(address)
                .name("다훈태정정답다")
                .build();
        memberRepository.save(member);

        OrderItem orderItem = OrderItem.createOrderItem(book, 2, 2);

        Delivery delivery = Delivery.builder()
                .address(address)
                .status(DeliveryStatus.COMP)
                .build();

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
    }
}
