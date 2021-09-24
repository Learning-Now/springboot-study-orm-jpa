<<<<<<< HEAD
package com.livenow.week5.repository;
import com.livenow.week5.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);

    List<Member> findByNameContainingOrderByNameDesc(String 게스트);
}
=======
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
>>>>>>> 5dc41a753dc0b546296c05ee079933e254b21ef0
