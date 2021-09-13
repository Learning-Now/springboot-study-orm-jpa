package service;

import domain.Delimiter;
import domain.Numbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_PATTERN = "[,;]";
    private static final String CUSTOM_PATTERN = "^//(.)\n";
    private static final String BLANK = "";
    private static final int GET_DELIMITER_COUNT = 1;
    private Delimiter customDelimiter;
    private Delimiter defaultDelimiter = new Delimiter(DEFAULT_PATTERN);

    public StringCalculator() {
    }

    public int add(String input) {
        if (isEqualsBlank(input)){
            return 0;
        }
        if (checkCustom(input)) {
            return new Numbers(customDelimiter.split(input)).sum();
        }
        return new Numbers(defaultDelimiter.split(input)).sum();
    }

    private boolean isEqualsBlank(String input) {
        return BLANK.equals(input);
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
}
