package com.livenow.week1.service;

import com.livenow.week1.controller.dto.MemberResponseDto;
import com.livenow.week1.controller.dto.MemberUpdateRequestDto;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private static Long AGE = 1L;

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(String name, int age) {
        Member member = Member.builder()
                .name(name)
                .age(age)
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id);
        return new MemberResponseDto(member);
    }

    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id);
        return member.update(requestDto);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id);
        member.getTeam().removeMember(member);
        memberRepository.remove(member);
    }
}