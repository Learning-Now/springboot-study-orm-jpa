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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;
    private String name;
    private int age;

    protected Member() {
    }

    public Member(String name, int age) {
        this(null, name, age, null);
    }

    public Member(String name, int age, Team team) {
        this(null, name, age, team);
    }

    public Member(Long id, String name, int age) {
        this(id, name, age, null);
    }

    @Builder
    public Member(Long id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public Member(Long id) {
    }

    public void changeTeam(Team team) {
        team.deleteMember(this);
        team.addMember(this);
        this.team = team;
    }


    public void deleteTeam() {
        team.deleteMember(this);
    }
}