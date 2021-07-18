package com.livenow.week1.service;

import com.livenow.week1.controller.dto.MemberFindResponseDto;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(String name, int age) {
        Member member = Member.builder()
                .name(name)
                .age(age)
                .build();
        memberRepository.save(member);
    }

    public List<MemberFindResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberFindResponseDto::new)
                .collect(Collectors.toList());
    }

    public MemberFindResponseDto findById(Long id) {
        return new MemberFindResponseDto(memberRepository.findById(id));
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id);
        memberRepository.delete(member);
    }
}
