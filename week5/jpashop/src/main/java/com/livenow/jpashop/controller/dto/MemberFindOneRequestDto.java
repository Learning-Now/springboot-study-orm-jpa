package com.livenow.jpashop.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberFindOneRequestDto {
    private Long id;

    protected MemberFindOneRequestDto() {
    }

    @Builder
    public MemberFindOneRequestDto(Long id) {
        this.id = id;
    }
}
