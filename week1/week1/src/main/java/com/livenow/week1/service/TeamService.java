package com.livenow.week1.service;

import com.livenow.week1.controller.team.dto.TeamSaveRequestDto;
import com.livenow.week1.controller.team.dto.TeamSaveResponseDto;
import com.livenow.week1.domain.MemberRepository;
import com.livenow.week1.domain.Team;
import com.livenow.week1.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public TeamSaveResponseDto save(TeamSaveRequestDto requestDto) {
        Team team = Team.builder()
                .name(requestDto.getName())
                .build();
        Team saveTeam = teamRepository.save(team);
        return new TeamSaveResponseDto(saveTeam);
    }
}
