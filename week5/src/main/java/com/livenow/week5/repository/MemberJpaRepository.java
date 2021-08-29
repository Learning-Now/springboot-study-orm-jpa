package com.livenow.week5.repository;

import com.livenow.week5.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Queue;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Queue<Member> findByName(String name);

    List<Member> findByNameLike(String name);


    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Member> findByNameContainingOrderByNameDesc(String name);
}
