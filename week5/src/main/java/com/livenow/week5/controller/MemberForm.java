package com.livenow.week5.controller;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다. ")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    @Builder
    public MemberForm(String name, String city, String street, String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
