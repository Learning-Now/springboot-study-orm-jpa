package com.livenow.week1.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    public Member(Long id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
    }

    public void deleteTeam() {
        this.team = null;
    }
}
