package com.livenow.week1.service;

import com.livenow.week1.controller.dto.MemberSaveResponseDto;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import com.livenow.week1.domain.Team;
import com.livenow.week1.domain.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


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
    void 저장() {
        //given
        Member member = Member.builder()
                .name("태정이형")
                .age(30)
                .team(new Team("우테코"))
                .build();
        MemberSaveResponseDto memberSaveResponseDto = new MemberSaveResponseDto(member);
        MemberService memberService = new MemberService(memberRepository);
        //when
        memberService.save(memberSaveResponseDto);
        Member findMember = memberRepository.findById(1L);
        //then
        Assertions.assertThat(member.getTeam()).isEqualTo(findMember.getTeam());
        System.out.println(findMember.getTeam().getName());
    }


}