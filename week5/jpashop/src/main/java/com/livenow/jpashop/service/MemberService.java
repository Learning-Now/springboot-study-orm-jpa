package com.livenow.jpashop.service;

import com.livenow.jpashop.controller.dto.MemberFindOneRequestDto;
import com.livenow.jpashop.controller.dto.MemberFindOneResponseDto;
import com.livenow.jpashop.controller.dto.MemberSaveRequestDto;
import com.livenow.jpashop.domain.Member;
import com.livenow.jpashop.domain.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Long join(MemberSaveRequestDto memberRequestDto){
        validateDuplicateMember(memberRequestDto);
        return memberRepository.save(memberRequestDto.toEntity());
    }

    public MemberFindOneResponseDto findOne(MemberFindOneRequestDto memberFindOneRequestDto) {
        Member member = memberRepository.findOne(memberFindOneRequestDto.getId());
        return new MemberFindOneResponseDto(member);
    }

    public List<MemberFindOneResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(member -> new MemberFindOneResponseDto(member))
                .collect(Collectors.toList());
    }

    //멤버 중복 검사
    private void validateDuplicateMember(MemberSaveRequestDto memberRequestDto) {
        List<Member> members = memberRepository.findByName(memberRequestDto.getName());
        if (!members.isEmpty()){
            throw new IllegalArgumentException("중복인 회원 존재");
        }
    }
}
