package com.livenow.week1.DTO;

import com.livenow.week1.domain.Member;
import lombok.Getter;

@Getter
public class MemberSaveResponseDto {
    private Long id;

    public MemberSaveResponseDto(Member entity) {
        this.id = entity.getId();
    }
}
