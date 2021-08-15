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
        Book book = new Book();
        book.setAuthor("정윤");
        book.setIsbn("isbn");
        book.setPrice(10000);
        book.setStockQuantity(20);
        book.setName("동현");
        itemRepository.save(book);

        Member member = new Member();
        Address address = new Address("시", "군", "구");
        member.setAddress(address);
        member.setName("다훈");
        memberRepository.save(member);
        OrderItem orderItem = OrderItem.createOrderItem(book, 2, 2);

        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.COMP);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
    }
}
