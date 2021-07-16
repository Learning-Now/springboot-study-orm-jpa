package com.livenow.week1.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MemberRepository {

    private final EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public void remove(Member member) {
        em.remove(member);
    }
}

