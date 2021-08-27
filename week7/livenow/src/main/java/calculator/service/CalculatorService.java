package calculator.service;

import calculator.domain.Delimiter;
import calculator.domain.Numbers;
import calculator.util.Input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorService {
    private static final String PATTERN ="//(.)\n(.*)";
    private static final int GET_DELIMITER_COUNT = 1;
    private static Delimiter basicDelimiter = new Delimiter("(.*),(.*)|(.*);(.*)");
    private Delimiter customDelimiter;


    public int stringCalculator(String bufferInput) {
        //bufferInput = Input.BufferInput();
        validateNull(bufferInput);
        checkCustomDelimiter(bufferInput);
        return calculator(splitBufferInput(bufferInput));
    }

    private int calculator(Numbers splitBufferInput) {
        return splitBufferInput.add();
    }

    public Numbers splitBufferInput(String bufferInput) {
        if (customDelimiter == null) {
            for (String s : basicDelimiter.split(bufferInput)) {
                System.out.println(s);
            }
            return new Numbers(basicDelimiter.split(bufferInput));
        }
        return new Numbers(customDelimiter.split(bufferInput.split("\n")[1]));
    }

    public boolean checkCustomDelimiter(String inputString) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(inputString);
        if (!matcher.find()) {
            validateHaveDelimiter(inputString);
            return false;
        }
        customDelimiter = new Delimiter(matcher.group(GET_DELIMITER_COUNT));
        return true;
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
        if (!inputString.matches("(.*),(.*)|(.*);(.*)")) {
            throw new IllegalArgumentException("Error : 구문자 존재 X");
        };
    }
}
