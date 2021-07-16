package com.livenow.week1.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
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
    public Member(Long id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
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
