package service;

import domain.Delimiter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_PATTERN = "[,;]";
    private static final String CUSTOM_PATTERN = "^//(.)\n";
    private static final int GET_DELIMITER_COUNT = 1;
    private Delimiter customDelimiter;

    public StringCalculator() {
    }

    public int add(String input) {
        if (input.isEmpty()){
            return 0;
        }
        if (checkCustom(input)) {
            return sum(split(splitInputByCustomPattern(input),customDelimiter.getValue()));
        }
        return sum(split(input,DEFAULT_PATTERN));
    }

    private String splitInputByCustomPattern(String input) {
        String[] split = input.split("\n");
        return split[1];
    }

    private boolean checkCustom(String input) {
        Pattern pattern = Pattern.compile(CUSTOM_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            customDelimiter = new Delimiter(matcher.group(GET_DELIMITER_COUNT));
            return true;
        }
        return false;
    }

    private String[] split(String input, String delimiter) {
        String[] numbers = input.split(delimiter);
        return numbers;
    }

    private int sum(String[] numbers) {
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
