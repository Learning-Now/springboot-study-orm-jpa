package com.livenow.week1.service;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

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
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id);
        memberRepository.delete(member);
    }
}
