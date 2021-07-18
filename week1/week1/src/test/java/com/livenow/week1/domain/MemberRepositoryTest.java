package com.livenow.week1.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntityManager em;

    @Test
    void memberGetTeam() {
        //given
        Member member = new Member(1L, "동현이 샛기", 3);
        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L);
        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }


    @Test
    void memberGetTeam2() {
        //given
        Member member = new Member(1L, "동현이 샛기", 3);
        Team team = new Team(1L, "배달의 민족");
        member.changeTeam(team);
        //when
        //teamRepository.save(team);
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L);
        em.flush();
        em.clear();
        Team team2 = teamRepository.findById(1L);
        //then
        Assertions.assertThat(findMember.getTeam()).isNotNull();
        Assertions.assertThat(team2).isNotNull();
    }
}
