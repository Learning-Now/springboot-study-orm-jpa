package com.livenow.week1.service;

import com.livenow.week1.controller.dto.MemberAddRequestDto;
import com.livenow.week1.controller.dto.MemberAddResponseDto;
import com.livenow.week1.controller.dto.MemberDeleteResponseDto;
import com.livenow.week1.controller.dto.TeamSaveResponseDto;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import com.livenow.week1.domain.Team;
import com.livenow.week1.domain.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public TeamService(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }

    public TeamSaveResponseDto save(String name) {
        Team team = Team.builder()
                .name(name)
                .build();
        return new TeamSaveResponseDto(teamRepository.save(team));
    }

    @Transactional
    public MemberAddResponseDto addMember(MemberAddRequestDto memberAddRequestDto) {
        Team team = teamRepository.findById(memberAddRequestDto.getTeamId());
        Member member = memberRepository.findById(memberAddRequestDto.getMemberId());
        member.changeTeam(team);
        return new MemberAddResponseDto(team, member);
    }

    public MemberDeleteResponseDto deleteMember(Long id) {
        Member member = memberRepository.findById(id);
        member.deleteTeam();
        return new MemberDeleteResponseDto(member);
    }
}
