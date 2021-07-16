package com.livenow.week1.domain;

import com.livenow.week1.service.TeamService;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
public class Team {

    @Id
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
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

    public static Optional<Object> findById(Long id) {
        return Team.findById(id);
    }


    public void setDeleted(String id) {
        TeamService.deleteMember(id);
    }
}