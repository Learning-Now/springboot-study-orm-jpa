package com.livenow.week1.domain;

import lombok.Getter;

<<<<<<< HEAD
import javax.persistence.*;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Team {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

=======
    @Column(name = "team_id")
    private Long id;
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    protected Team() {
    }

<<<<<<< HEAD

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
=======
    public Team(String name) {
        this(null, name);
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
    }
}
