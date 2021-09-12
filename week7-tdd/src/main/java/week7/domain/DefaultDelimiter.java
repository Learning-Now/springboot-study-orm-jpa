package week7.domain;

public class DefaultDelimiter extends Delimiter{

    private static final String DEFAULT_DELIMITER = ",|:";

    public DefaultDelimiter() {
        value = DEFAULT_DELIMITER;
    }

    public String[] split(String input) {
        return input.split(value);
    }
}