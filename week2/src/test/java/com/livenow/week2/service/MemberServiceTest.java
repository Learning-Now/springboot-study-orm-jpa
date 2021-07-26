package com.livenow.week2.service;

import com.livenow.week2.domain.Member;
import com.livenow.week2.domain.MemberRepository;
import com.livenow.week2.domain.Team;
import com.livenow.week2.domain.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntityManager em;

    @Test
    void memberProxy() {
        //given
        Member member = Member.builder().name("동현").age(32).build();
        memberRepository.save(member);

        Team team = Team.builder().name("우리팀!").build();
        teamRepository.save(team);
        member.changeTeam(team);

        em.flush();
        em.clear();
        em.close();
        //when
        Member findMember = em.find(Member.class, 1L);
        //then
        System.out.println("findMember = " + findMember.getClass().getName());
        System.out.println("findTeamPROXY = " + findMember.getTeam().getClass().getName());

        System.out.println(" 쿼리 나감 --------------");
        findMember.getTeam().getName();
        System.out.println("findTeam = " +findMember.getTeam().getClass().getName());
    }

    @Test
    void persistTest() {
        //given
        Member member = Member.builder().name("동현").age(32).build();
        Team team = Team.builder().name("우리팀!").build();
        member.changeTeam(team);
        teamRepository.save(team);

        em.flush();
        em.clear();
        em.close();

        Member member1 = memberRepository.findById(member.getId());
        System.out.println("member1 = " + member1);
    }

    @Test
    void equalsTest() {
        //given
        Member member = Member.builder().name("동현").age(32).build();
        Team team = Team.builder().name("우리팀!").build();
        member.changeTeam(team);
        teamRepository.save(team);

        em.flush();
        em.clear();
        em.close();

        Member member1 = memberRepository.findById(member.getId());
        Assertions.assertThat(member).isEqualTo(member1);
    }
}