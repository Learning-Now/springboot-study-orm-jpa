package com.livenow.week1.domain;

<<<<<<< HEAD
import com.livenow.week1.controller.dto.MemberUpdateRequestDto;
import lombok.Builder;
import lombok.Generated;
=======
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

<<<<<<< HEAD
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
=======
    @Id
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    private String name;
<<<<<<< HEAD

    private int age;

    public Member() {
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


=======
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
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
}
