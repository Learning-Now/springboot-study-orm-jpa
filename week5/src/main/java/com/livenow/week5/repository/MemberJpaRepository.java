package com.livenow.week5.repository;

import com.livenow.week5.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);

    List<Member> findByNameLike(String name);

    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Member> findByNameContainingOrderByNameDesc(String name);
}
