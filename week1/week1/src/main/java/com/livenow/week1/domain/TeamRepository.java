package com.livenow.week1.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class TeamRepository {

    private final EntityManager em;

    public TeamRepository(EntityManager em) {
        this.em = em;
    }

    public Team save(Team team) {
        em.persist(team);
        return team;
    }

    public Team findById(Long id) {
        return em.find(Team.class, id);
    }
}
