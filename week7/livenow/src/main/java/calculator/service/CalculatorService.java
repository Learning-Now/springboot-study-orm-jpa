package calculator.service;

import calculator.domain.Delimiter;
import calculator.util.Input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorService {
    private final String PATTERN ="//(.)\n(.*)";
    private final int GET_DELIMITER_COUNT = 1;

    public void stringCalculator() {
        validateNull(Input.BufferInput());
    }

    public Delimiter checkCustomDelimiter(String inputString) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(inputString);
        if (!matcher.find()) {
            validateHaveDelimiter(inputString);
        }
        return new Delimiter(matcher.group(GET_DELIMITER_COUNT));
    }

    private void validateNull(String inputString) {
        if (inputString == null) {
            throw new IllegalArgumentException("Error : null");
        }

        if (inputString == "") {
            throw new IllegalArgumentException("Error : 아무것도 입력되지 않음");
        }
    }

    private void validateHaveDelimiter(String inputString) {
        if (!inputString.contains(",|;")) {
            throw new IllegalArgumentException("Error : 구분자가 존재하지 않음");
        };
    }
}
