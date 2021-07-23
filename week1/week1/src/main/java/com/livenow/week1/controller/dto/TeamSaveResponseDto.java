package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Team;
import lombok.Getter;

@Getter
public class TeamSaveResponseDto {

    private Long id;

    public TeamSaveResponseDto(Team entity) {
        this.id = entity.getId();
    }
}
