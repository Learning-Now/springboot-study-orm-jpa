package com.livenow.week2.service;

import com.livenow.week2.domain.Member;
import com.livenow.week2.domain.MemberRepository;
import com.livenow.week2.domain.Team;
import com.livenow.week2.domain.TeamRepository;
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

    @Transactional
    public void save(String name) {
        Team team = Team.builder().name(name).build();
        teamRepository.save(team);
    }

    @Transactional
    public void addMember(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId);
        Member member = memberRepository.findById(memberId);
        team.addMember(member);
    }
}
