package com.livenow.jpashop.controller.dto;

import com.livenow.jpashop.domain.Address;
import com.livenow.jpashop.domain.Member;
import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    private String name;
    private Address address;

    public MemberSaveRequestDto(Member member) {
        this.name = member.getName();
        this.address = member.getAddress();
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address(this.address)
                .build();
    }
}
