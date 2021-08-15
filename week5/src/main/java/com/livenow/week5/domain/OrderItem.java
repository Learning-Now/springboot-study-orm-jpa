package com.livenow.week5.domain;

import com.livenow.week5.domain.item.Item;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //이렇게 제한하면서 짜야지, 누군가 잘못 접근할 때 제한할 수 있다.
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)  //Manythoone은 default가 Eager이기 때문에 다바꿔야함 ctrl+shift+f 로 찾기
    @JoinColumn(name = "item_id")    //FK 설정
    private Item item;

    @ManyToOne(fetch = LAZY)  //Manythoone은 default가 Eager이기 때문에 다바꿔야함
    @JoinColumn(name = "order_id")    //FK 설정
    private Order order;

    private int orderPrice;
    private int count;

    @Builder
    public OrderItem(Long id, Item item, Order order, int orderPrice, int count) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    protected OrderItem(Long id) {
    }

    //==생성 메서드 == //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
                                            .item(item)
                                            .orderPrice(orderPrice)
                                            .count(count)
                                            .build();
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직 ==//
    //주문취소
    public void cancel() {
        getItem().addStock(count);
    }

    //==w조회 로직 ==//
    //주문 상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
