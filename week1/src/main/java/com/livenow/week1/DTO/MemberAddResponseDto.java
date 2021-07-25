package com.livenow.week1.DTO;

import com.livenow.week1.domain.Member;

import com.livenow.week1.domain.Team;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberAddResponseDto {
    private Long memberId;
    private Long teamId;

    public MemberAddResponseDto( Member memberEntity,Team teamEntity) {
        this.memberId = memberEntity.getId();
        this.teamId=teamEntity.getId();
    }

    public MemberAddResponseDto(List<Member> members, Long id) {
    }

}
