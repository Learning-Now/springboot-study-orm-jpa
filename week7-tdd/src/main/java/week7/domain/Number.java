package week7.domain;

public class Number {

    private static final int ZERO = 0;
    private int value;

    public Number(int value) {
        validate(value);
        this.value = value;
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
