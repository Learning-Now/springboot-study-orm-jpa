package com.livenow.week1.controller.member.dto;

import com.livenow.week1.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDeleteResponseDto {

    private Long id;

    public MemberDeleteResponseDto(Member member) {
        this.id = member.getId();
    }
}
