package com.livenow.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
