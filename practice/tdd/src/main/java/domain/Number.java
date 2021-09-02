package domain;

public class Number {
    private final int value;

    public Number(String stringNumber) {
        value = Integer.parseInt(stringNumber);
    }

    public int getValue() {
        return value;
    }
}
