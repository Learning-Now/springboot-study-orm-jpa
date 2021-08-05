package com.livenow.jpashop.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();

    protected Member() {
    }

    @Builder
    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

}
