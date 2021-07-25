package com.livenow.week1.controller.team.dto;

import com.livenow.week1.domain.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamSaveResponseDto {

    private Long id;
    private String name;

    public TeamSaveResponseDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }
}
