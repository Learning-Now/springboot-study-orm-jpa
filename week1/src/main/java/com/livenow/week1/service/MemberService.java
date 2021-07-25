package com.livenow.week1.service;

import com.livenow.week1.DTO.MemberSaveResponseDto;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.livenow.week1.DTO.MemberDeleteResponseDto;
import com.livenow.week1.DTO.MemberFindeResponseDto;
import com.livenow.week1.DTO.MemberSaveRequestDto;
import com.livenow.week1.DTO.MemberSaveResponseDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private static Long AGE = 1L;

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

    public MemberFindeResponseDto findById(Long id) {
        return new MemberFindeResponseDto(memberRepository.findById(id));
    }

    @Transactional
    public MemberDeleteResponseDto delete(Long id) {
        Member member = memberRepository.findById(id);
        return new MemberDeleteResponseDto(memberRepository.delete(member));
    }

    public void save(String name, int age) {
        Member member = new Member(AGE++, name, age);
        memberRepository.save(member);
    }

    public void inquire(String name, int age){
        Member member=new Member(name,age) ;
        memberRepository.findById(member.getId());
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }
    @Transactional
    public static void delete(Integer id){
        MemberRepository.delete(id);
    }
    public List<MemberFindeResponseDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberFindeResponseDto::new)
                .collect(Collectors.toList());
    }
}