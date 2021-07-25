package com.livenow.week1.DTO;

import lombok.Getter;
import com.livenow.week1.domain.Member;
import com.livenow.week1.domain.Team;

@Getter
public class MemberAddRequestDto {
    private Long memberId;
    private Long teamId;

    public MemberAddRequestDto(Member member,Team team) {
        this.memberId = member.getId();
        this.teamId = team.getId();
    }
}
