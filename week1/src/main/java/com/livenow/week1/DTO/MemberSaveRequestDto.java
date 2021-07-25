package com.livenow.week1.DTO;

import lombok.Getter;
import com.livenow.week1.domain.Member;

@Getter
public class MemberSaveRequestDto {
    private String name;
    private int age;

    public MemberSaveRequestDto(Member member){
        this.name=member.getName();
        this.age=member.getAge();
    }
}
