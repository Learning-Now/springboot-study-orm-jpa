package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import lombok.Getter;

@Getter
public class MemberDeleteRequestDto {

    private Long memberId;

    public MemberDeleteRequestDto(Member entity) {
        this.memberId = entity.getId();
    }

    public Member toEntity() {
        return Member.builder()
                .id(memberId)
                .build();
    }
}
