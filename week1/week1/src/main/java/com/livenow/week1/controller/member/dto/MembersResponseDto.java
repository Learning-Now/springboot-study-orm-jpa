package com.livenow.week1.controller.member.dto;

import com.livenow.week1.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembersResponseDto {

    private List<MemberResponseDto> members;

    public MembersResponseDto(List<Member> members) {
        this.members = members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }
}
