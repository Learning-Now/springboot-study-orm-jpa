package com.livenow.week1.DTO;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.Team;
import lombok.Getter;

@Getter
public class MemberFindeResponseDto {

    private Long id;
    private String name;
    private int age;
    private Team team;

    public MemberFindeResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.team = entity.getTeam();
    }
}
