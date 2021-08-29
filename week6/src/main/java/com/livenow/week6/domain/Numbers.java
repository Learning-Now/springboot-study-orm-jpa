package com.livenow.week6.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Numbers {

    private List<Number> numbers;

    public Numbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public int add() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }
}
