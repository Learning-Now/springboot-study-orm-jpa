package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    private String name;
    private int age;

    public MemberSaveRequestDto(Member entity) {
        this.name = entity.getName();
        this.age = entity.getAge();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .build();
    }
}
