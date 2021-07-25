package com.livenow.week1.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicResponseDto<T> {

    private T data;

    private BasicResponseDto(T data) {
        this.data = data;
    }

    public static <T> BasicResponseDto<T> of(final T data) {
        return new BasicResponseDto<>(data);
    }
}
