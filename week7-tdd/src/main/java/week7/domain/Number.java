package week7.domain;

public class Number {

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
        if (number <= 0) {
            throw new RuntimeException();
        }
        return number;
    }

    public int getValue() {
        return value;
    }
}
