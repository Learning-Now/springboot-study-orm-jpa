package com.livenow.week5.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static com.livenow.week5.domain.OrderSpec.memberNameLike;
import static com.livenow.week5.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specification.where;

@Getter
@Setter
public class OrderSearch {
    private OrderStatus orderStatus;
    private String memberName;

    public Specification<Order> toSpecification() {
        return where (memberNameLike(memberName))
                .and(orderStatusEq(orderStatus));
    }
}
