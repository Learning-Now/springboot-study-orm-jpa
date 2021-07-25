package com.livenow.week1.controller.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveRequestDto {

    private String name;
    private Integer age;
    private Long teamId;

    public MemberSaveRequestDto(String name, Integer age, Long teamId) {
        this.name = name;
        this.age = age;
        this.teamId = teamId;
    }
}
