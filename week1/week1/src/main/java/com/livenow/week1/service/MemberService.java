package com.livenow.week1.service;

import com.livenow.week1.controller.dto.MemberDeleteResponseDto;
import com.livenow.week1.controller.dto.MemberFindResponseDto;
import com.livenow.week1.controller.dto.MemberSaveRequestDto;
import com.livenow.week1.controller.dto.MemberSaveResponseDto;
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

    public MemberSaveResponseDto save(MemberSaveRequestDto memberSaveRequestDto) {
        Member member = Member.builder()
                .name(memberSaveRequestDto.getName())
                .age(memberSaveRequestDto.getAge())
                .build();
        return new MemberSaveResponseDto(memberRepository.save(member));
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
    public MemberDeleteResponseDto delete(Long id) {
        Member member = memberRepository.findById(id);
        return new MemberDeleteResponseDto(memberRepository.delete(member));
    }
}
