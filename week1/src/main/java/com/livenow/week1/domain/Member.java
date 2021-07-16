package com.livenow.week1.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    //EAGER 은 하나의 객체를 DB로부터 읽어올때 참조 객체들의 데이터까지 전부 읽어오는 방식
    //LAZY는  참조 객체들의 데이터들은 무시하고 해당 엔티티의 데이터만을 갖고온다

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

    public Member(Long id) {
    }

    public void changeTeam(Team team) {
        this.team = team;
    }


}