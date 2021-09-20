package util;

public class Converter {
    private static final String NUMBER_PATTERN = "^[0-9]*$";
    private static final String NUMBER_ERROR_MESSAGE = "Error : 숫자가 아닌값이 입력되었습니다.";

    private Converter() {
    }

    public static int stringToInt(String stringNumber) {
        validateParseNumber(stringNumber);
        return Integer.parseInt(stringNumber);
    }

    public static void validateParseNumber(String number) {
        if(isNumber(number)) {
            throw new RuntimeException(NUMBER_ERROR_MESSAGE);
        };
    }
    public static boolean isNumber(String number) {
        return !number.matches(NUMBER_PATTERN);
    }

}
