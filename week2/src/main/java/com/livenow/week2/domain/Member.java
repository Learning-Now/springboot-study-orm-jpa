package com.livenow.week2.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String name;
    private int age;

    @Builder
    public Member(Long id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        changeTeam(team);
    }

    public void changeTeam(Team team) {
        if (Objects.isNull(team)) {
            return;
        }
        if (Objects.nonNull(this.team)) {
            this.team.getMembers().remove(this);
        }
        team.getMembers().add(this);
        this.team = team;
    }
}
