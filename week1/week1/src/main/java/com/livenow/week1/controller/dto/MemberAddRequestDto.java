package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.Team;
import lombok.Getter;

@Getter
public class MemberAddRequestDto {

    private Long teamId;
    private Long memberId;

    public MemberAddRequestDto(Team teamEntity, Member memberEntity) {
        this.teamId = teamEntity.getId();
        this.memberId = memberEntity.getId();
    }
}
