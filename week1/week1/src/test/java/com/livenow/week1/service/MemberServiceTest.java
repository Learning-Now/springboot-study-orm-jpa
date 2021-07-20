package com.livenow.week1.service;

import com.livenow.week1.controller.dto.MemberSaveRequestDto;
import com.livenow.week1.controller.dto.MemberUpdateRequestDto;
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
        MemberSaveRequestDto memberSaveResponseDto = new MemberSaveRequestDto(member);
        MemberService memberService = new MemberService(memberRepository);
        //when
        memberService.save(memberSaveResponseDto);
        Member findMember = memberRepository.findById(1L);
        //then
        Assertions.assertThat(member.getTeam()).isEqualTo(findMember.getTeam());
        System.out.println(findMember.getTeam().getName());
    }

    @Test
    void 수정() {
        //given
        Member member = Member.builder()
                .name("다훈이형")
                .age(30)
                .team(new Team("호두마루"))
                .build();
        MemberSaveRequestDto memberSaveResponseDto = new MemberSaveRequestDto(member);
        MemberService memberService = new MemberService(memberRepository);
        //when
        memberService.save(memberSaveResponseDto);
        Member findMember = memberRepository.findById(1L);
        MemberUpdateRequestDto memberUpdateRequestDto = MemberUpdateRequestDto.builder()
                .age(29)
                .team(new Team("삼성sds"))
                .build();
        memberService.update(1L, memberUpdateRequestDto);
        //then
        Assertions.assertThat("삼성sds").isEqualTo(findMember.getTeam().getName());
        System.out.println(findMember.getTeam().getName());
    }

    @Test
    void 삭제() {
        //given
        Member member = Member.builder()
                .name("다훈이형")
                .age(30)
                .team(new Team("호두마루"))
                .build();
        MemberSaveRequestDto memberSaveResponseDto = new MemberSaveRequestDto(member);
        MemberService memberService = new MemberService(memberRepository);
        //when
        memberService.save(memberSaveResponseDto);

        memberService.delete(1L);
        Member member1 = memberRepository.findById(1L);
        //then
        Assertions.assertThat(member1).isNull();
    }


}