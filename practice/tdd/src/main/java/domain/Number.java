package domain;

import util.Converter;

import java.util.Objects;

public class Number {
    private static final String NEGATIVE_ERROR_MESSAGE = "Error : 음수는 입력될수 없음.";
    private static final int ZERO = 0;
    private final int value;

    public Number(String number) {
        this(Converter.stringToInt(number));
    }

    public Number(int number) {
        validateNumber(number);
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    private void validateNumber(int number) {
        if (number < ZERO) {
            throw new RuntimeException(NEGATIVE_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
