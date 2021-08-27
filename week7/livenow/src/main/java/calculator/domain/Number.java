package calculator.domain;

public class Number {
    private final int value;

    public Number(int input) {
        validateNumber(input);
        this.value = input;
    }

    private void validateNumber(int input) {
        if (input < 0) {
            throw new RuntimeException("Error : 음수 입력");
        }
    }

    protected int getValue() {
        return value;
    }
}
