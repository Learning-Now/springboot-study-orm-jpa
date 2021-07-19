package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {
    private String name;
    private int age;
    private Team team;

    public MemberUpdateRequestDto() {

    }

    @Builder
    public MemberUpdateRequestDto(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

}
