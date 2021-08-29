package com.livenow.week6.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomDelimiter {

    private String value = ",|:";

    public CustomDelimiter(String value) {
        this.value += "|" + value;
    }
}
