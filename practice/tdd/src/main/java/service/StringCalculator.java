package service;

import domain.Delimiter;
import domain.Numbers;

public class StringCalculator {
    private static final String BLANK = "";

    public StringCalculator() {
    }

    public int add(String input) {
        if (isEqualsBlank(input)){
            return 0;
        }
        return new Numbers(new Delimiter(input).split(input)).sum();
    }

    private boolean isEqualsBlank(String input) {
        return BLANK.equals(input);
    }
}
