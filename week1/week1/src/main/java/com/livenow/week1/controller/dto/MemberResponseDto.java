package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Team;

public class MemberResponseDto {
    private Long id;
    private String name;
    private int age;
    private Team team;

    public MemberResponseDto(Long id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }
}
