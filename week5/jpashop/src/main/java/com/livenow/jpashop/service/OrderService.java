package com.livenow.jpashop.service;

import com.livenow.jpashop.domain.Delivery;
import com.livenow.jpashop.domain.Member;
import com.livenow.jpashop.domain.Order;
import com.livenow.jpashop.domain.OrderItem;
import com.livenow.jpashop.domain.item.Item;
import com.livenow.jpashop.domain.repository.MemberRepository;
import com.livenow.jpashop.domain.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemService itemService;

    //주문
    public Long order(Long memberId, Long itemId, int count){
        Member findMember = memberRepository.findOne(memberId);
        Item findItem = itemService.findOne(itemId);
        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);
        Delivery delivery = new Delivery(findMember.getAddress());
        Order order = Order.createOrder(findMember, delivery, orderItem);
        return orderRepository.save(order);


    }
    //취소
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
    //주문 검색
    public List<Order> findOrders(){
        return orderRepository.findAll();
    }

}
