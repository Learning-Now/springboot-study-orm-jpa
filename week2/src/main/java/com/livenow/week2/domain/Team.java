package com.livenow.week2.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Member> members = new ArrayList<>();

    @Builder
    public Team(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void addMember(Member member) {
        member.changeTeam(this);
        if (!this.members.contains(member)) {
            this.members.add(member);
        }
    }
}
