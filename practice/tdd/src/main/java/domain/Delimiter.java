package domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delimiter {
    private static final String DEFAULT_PATTERN = "[,;]";
    private static final String CUSTOM_PATTERN = "^//(.)\n";
    private static final int GET_DELIMITER_COUNT = 1;
    private String value;

    public Delimiter(String input) {
        if (!checkCustom(input)) {
            this.value = DEFAULT_PATTERN;
        }
    }

    public String[] split(String input) {
        if (!checkDefaultValue()) {
            return input.split(CUSTOM_PATTERN)[1].split(this.value);
        }
        return input.split(this.value);
    }

    private boolean checkDefaultValue() {
        if (this.value.equals(DEFAULT_PATTERN)) {
            return true;
        }
        return false;
    }

    private boolean checkCustom(String input) {
        Matcher matcher = Pattern.compile(CUSTOM_PATTERN).matcher(input);
        if (matcher.find()) {
            this.value = matcher.group(GET_DELIMITER_COUNT);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delimiter delimiter = (Delimiter) o;
        return Objects.equals(value, delimiter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}