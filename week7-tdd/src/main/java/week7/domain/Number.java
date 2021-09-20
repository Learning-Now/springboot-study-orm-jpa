package week7.domain;

import week7.util.Converter;

public class Number {

    private static final int ZERO = 0;
    private int value;

    public Number(int value) {
        validate(value);
        this.value = value;
    }

    public Number(String value) {
        int number = Converter.toInt(value);
        validate(number);
        this.value = number;
    }

    private void validate(int value) {
        if (value < ZERO) {
            throw new RuntimeException("입력 값에 음수가 포함되어 있습니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
