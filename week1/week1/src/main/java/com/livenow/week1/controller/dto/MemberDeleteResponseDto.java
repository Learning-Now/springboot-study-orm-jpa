package com.livenow.week1.controller.dto;

import com.livenow.week1.domain.Member;
import lombok.Getter;

@Getter
public class MemberDeleteResponseDto {

    private Long id;

    public MemberDeleteResponseDto(Member entity) {
        this.id = entity.getId();
    }
}
