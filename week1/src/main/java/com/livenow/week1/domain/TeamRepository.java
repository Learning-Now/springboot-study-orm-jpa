package com.livenow.week1.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TeamRepository {

    private final EntityManager em;

    public TeamRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Team team) {
        em.persist(team);
    }

    public Team findById(Long id) {
        return em.find(Team.class, id);
    }
}