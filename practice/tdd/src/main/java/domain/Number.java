package domain;

public class Number {
    private static final String NUMBER_PATTERN = "^[0-9]*$";
    private static final String RUNTIME_ERROR_MESSAGE = "Error : 음수는 입력될수 없음.";
    private static final String NUMBER_ERROR_MESSAGE = "Error : 숫자가 아닌값이 입력되었습니다.";
    private static final int ZERO = 0;
    private final int value;

    public Number(String number) {
        this(stringToInt(number));
    }

    public Number(int number) {
        validateNumber(number);
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    private static int stringToInt(String stringNumber) {
        validateParseNumber(stringNumber);
        return Integer.parseInt(stringNumber);
    }

    private static void validateParseNumber(String number) {
        if(isNumber(number)) {
            throw new IllegalArgumentException(NUMBER_ERROR_MESSAGE);
        };
    }

    private static boolean isNumber(String number) {
        return !number.matches(NUMBER_PATTERN);
    }

    private void validateNumber(int number) {
        if (number < ZERO) {
            throw new RuntimeException(RUNTIME_ERROR_MESSAGE);
        }
    }
}
