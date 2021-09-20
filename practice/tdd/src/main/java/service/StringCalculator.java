package service;

import domain.CustomDelimiter;
import domain.DefaultDelimiter;
import domain.Delimiter;
import domain.Numbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String CUSTOM_PATTERN = "^//(.)\n";
    private static final String BLANK = "";
    private static final int GET_DELIMITER_COUNT = 1;
    private Delimiter customDelimiter;

    public StringCalculator() {
    }

    public int add(String input) {
        if (isEqualsBlank(input)){
            return 0;
        }
        if (checkCustom(input)) {
            return new Numbers(customDelimiter.split(extractInput(input))).sum();
        }
        return new Numbers(new DefaultDelimiter().split(input)).sum();
    }

    private String extractInput(String input) {
        Matcher matcher = findMatcher(input);
        if (matcher.find()) {
            return input.substring(matcher.end());
        }
        return input;
    }

    private boolean isEqualsBlank(String input) {
        return BLANK.equals(input);
    }

    private boolean checkCustom(String input) {
        Matcher matcher = findMatcher(input);
        if (matcher.find()) {
            customDelimiter = new CustomDelimiter(matcher.group(GET_DELIMITER_COUNT));
            return true;
        }
        return false;
    }

    private Matcher findMatcher(String input) {
        Pattern pattern = Pattern.compile(CUSTOM_PATTERN);
        return pattern.matcher(input);
    }
}
