package com.livenow.week1.service;

import com.livenow.week1.controller.member.dto.*;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import com.livenow.week1.domain.Team;
import com.livenow.week1.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public MemberSaveResponseDto saveMember(MemberSaveRequestDto requestDto) {
        Team findTeam = teamRepository.findById(requestDto.getTeamId());
        if (findTeam == null) {
            Member member = Member.builder()
                    .name(requestDto.getName())
                    .age(requestDto.getAge())
                    .build();
            Member saveMember = memberRepository.save(member);
            return new MemberSaveResponseDto(saveMember);
        }
        Member member = Member.builder()
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .team(findTeam)
                .build();
        Member saveMember = memberRepository.save(member);
        return new MemberSaveResponseDto(saveMember);
    }

    @Transactional(readOnly = true)
    public MembersResponseDto findAllMember() {
        return new MembersResponseDto(memberRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId);
        if (findMember == null) {
            throw new IllegalArgumentException();
        }
        return new MemberResponseDto(findMember);
    }

    public MemberDeleteResponseDto deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId);
        if (findMember == null) {
            throw new IllegalArgumentException();
        }
        Member deletedMember = memberRepository.deleteMember(findMember);
        return new MemberDeleteResponseDto(deletedMember);
    }
}
