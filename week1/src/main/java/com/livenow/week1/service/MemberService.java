package com.livenow.week1.service;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private static Long AGE = 1L;

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(String name, int age) {
        Member member = new Member(AGE++, name, age);
        memberRepository.save(member);
    }
    @Transactional
    public void inquire(String name, int age){
        Member member=new Member(name,age) ;
        memberRepository.findById(member.getId());
    }
    @Transactional
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }
}