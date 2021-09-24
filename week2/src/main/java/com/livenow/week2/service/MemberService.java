package com.livenow.week2.service;

import com.livenow.week2.domain.Member;
import com.livenow.week2.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(String name, int age) {
        Member member = Member.builder()
                .name(name).age(age)
                .build();
        memberRepository.save(member);
    }
}
