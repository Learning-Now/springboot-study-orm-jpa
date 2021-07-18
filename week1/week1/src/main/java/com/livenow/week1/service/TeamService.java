package com.livenow.week1.service;

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

    public void save(String name) {
        Team team = Team.builder()
                .name(name)
                .build();
        teamRepository.save(team);
    }

    @Transactional
    public void addMember(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId);
        Member member = memberRepository.findById(memberId);
        member.changeTeam(team);

        team.addMember(member);
    }

    @Transactional
    public void deleteMember(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId);
        Member member = memberRepository.findById(memberId);
        member.deleteTeam();

        team.deleteMember(member);
    }
}
