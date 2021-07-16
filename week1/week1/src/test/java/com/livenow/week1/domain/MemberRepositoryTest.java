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
        Member member = new Member().builder()
                .age(1)
                .name("태정바보")
                .team(new Team("배민"))
                .build();
        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L);
        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }


    @Test
    void memberGetTeam2() {
        //given
        Member member = new Member().builder()
                .age(1)
                .name("태정바보")
                .team(new Team("배민"))
                .build();
        Team team = new Team("배달의 민족");
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