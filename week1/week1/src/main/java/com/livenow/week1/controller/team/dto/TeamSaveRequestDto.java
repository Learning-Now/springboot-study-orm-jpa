package com.livenow.week1.controller.team.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamSaveRequestDto {

    private String name;

    public TeamSaveRequestDto(String name) {
        this.name = name;
    }
}
