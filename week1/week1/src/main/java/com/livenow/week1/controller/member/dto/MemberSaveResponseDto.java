package com.livenow.week1.controller.member.dto;

import com.livenow.week1.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveResponseDto {

    private Long id;

    public MemberSaveResponseDto(Member member) {
        this.id = member.getId();
    }
}
