package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.Team;

public class MemberResponseDto {
    private Long id;
    private String name;
    private int age;
    private Team team;

    public MemberResponseDto() {
    }

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.team = member.getTeam();
    }
}
