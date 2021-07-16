package com.livenow.week1.service;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.MemberRepository;
import com.livenow.week1.domain.Team;
import com.livenow.week1.domain.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class TeamService {

    private static Long AGE = 1L;

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public TeamService(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void save(String name) {
        Team team = new Team(AGE++, name);
        teamRepository.save(team);
    }

    @Transactional
    public void addMember(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId);
        Member member = memberRepository.findById(memberId);
        member.changeTeam(team);

        team.getMembers().add(member);
    }

    @Transactional
    public static void deleteMember(String memberId) {
        TeamService.deleteMember(memberId);
    }

}