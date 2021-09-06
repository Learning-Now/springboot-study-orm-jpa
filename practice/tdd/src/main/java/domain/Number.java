package domain;

public class Number {
    private static final String NUMBER_PATTERN = "^[0-9]*$";
    private final int value;

    public Number(String number) {
        this(stringToInt(number));
    }

    public Number(int number) {
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    private static int stringToInt(String stringNumber) {
        return Integer.parseInt(stringNumber);
    }

    private void validateNumber(String stringNumber) {
        if(!stringNumber.matches(NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Error : 숫자가 아닌값이 입력되었습니다.");
        };
    }
}
