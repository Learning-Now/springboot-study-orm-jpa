package com.livenow.week5.service;

import com.livenow.week5.domain.Delivery;
import com.livenow.week5.domain.Member;
import com.livenow.week5.domain.Order;
import com.livenow.week5.domain.OrderItem;
import com.livenow.week5.domain.item.Item;
<<<<<<< HEAD
import com.livenow.week5.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.livenow.week5.repository.OrderJpaRepository;
=======
import com.livenow.week5.repository.ItemRepository;
import com.livenow.week5.repository.MemberRepository;
import com.livenow.week5.repository.OrderRepository;
import com.livenow.week5.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 5dc41a753dc0b546296c05ee079933e254b21ef0

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

<<<<<<< HEAD
    private final OrderJpaRepository orderJpaRepository;
=======
>>>>>>> 5dc41a753dc0b546296c05ee079933e254b21ef0
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성   셍상 메서드를 사용해보자
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);  // 엔티티가 비즈니스 로직을 가지고 객체 지향의 특성을 적극 활용하는 것을 도메인 모델 패턴이라함.

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);     // 엔티티가 비즈니스 로직을 가지고 객체 지향의 특성을 적극 활용하는 것을 도메인 모델 패턴이라함.

        //주문 저장
<<<<<<< HEAD
        orderJpaRepository.save(order);
      //이전에 cascade를 해줬기 때문에 이것만 해줘도 orderitem 랑 delivery가 자동으로 persist가됨
=======
        orderRepository.save(order);        //이전에 cascade를 해줬기 때문에 이것만 해줘도 orderitem 랑 delivery가 자동으로 persist가됨
>>>>>>> 5dc41a753dc0b546296c05ee079933e254b21ef0
        //cascade를  언제써야할가? 주인이 private owner일 때만
        return order.getId();          //lifecycle이 같이 persist를 할때 주로 씀

    }

    /**
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
<<<<<<< HEAD
        Order order = orderJpaRepository.findById(orderId).get();
=======
        Order order = orderRepository.findOne(orderId);
>>>>>>> 5dc41a753dc0b546296c05ee079933e254b21ef0
        //주문 취소
        order.cancel();
    }

    //검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
