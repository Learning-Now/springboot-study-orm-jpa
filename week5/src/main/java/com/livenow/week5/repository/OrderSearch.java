package com.livenow.week5.repository;

import com.livenow.week5.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;  //회원이름
    private OrderStatus orderStatus; //주문상태[ORDER, CANCEL]
}
