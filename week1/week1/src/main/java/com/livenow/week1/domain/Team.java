package com.livenow.week1.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Member> members = new ArrayList<>();

    protected Team() {
    }

    public Team(String name) {
        this(null, name);
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void deleteMember(Member member) {
        members.remove(member);
    }
}
