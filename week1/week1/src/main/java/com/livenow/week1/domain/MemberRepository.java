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
<<<<<<< HEAD

    public void remove(Member member) {
        em.remove(member);
    }
=======
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
}

