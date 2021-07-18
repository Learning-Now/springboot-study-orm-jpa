package com.livenow.week1.domain;


import com.livenow.week1.controller.dto.MemberUpdateRequestDto;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    private String name;

    private int age;

    protected Member() {
    }

    @Builder
    public Member(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }
    public Long update(MemberUpdateRequestDto requestDto) {
        this.age = requestDto.getAge();
        this.name = requestDto.getName();
        this.team = requestDto.getTeam();
        return this.id;
    }

    public void changeTeam(Team team) {
        this.team = team;
    }

    //연관관계 편의 메소드
    public void setTeam(Team team) {
        if (this.team == null) {
            this.team.removeMember(this);
        }
        this.team = team;
        team.addMember(this);
    }
}
