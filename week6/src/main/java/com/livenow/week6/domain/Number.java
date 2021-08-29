package com.livenow.week6.domain;

public class Number {

    private static final int ZERO = 0;
    private int value;

    public Number(String value) {
        this.value = validate(value);
    }

    private int validate(String value) {
        int number;
        try {
            number = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }
        if (isNegative(number)) {
            throw new RuntimeException();
        }
        return number;
    }

    private boolean isNegative(int number) {
        return number < ZERO;
    }

    public int getValue() {
        return value;
    }
}
