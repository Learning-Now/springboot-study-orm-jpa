package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.Team;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {
    private String name;
    private int age;
    private Team team;


    public MemberUpdateRequestDto(Member member) {
        this.name = member.getName();
        this.age = member.getAge();
        this.team = member.getTeam();
    }
}
