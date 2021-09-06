package week7.domain;

public class Delimiter {

    private static final String DEFAULT_DELIMITER = ",|:";
    private String value;

    public Delimiter() {
        this.value = DEFAULT_DELIMITER;
    }

    public Delimiter(String value) {
        this.value = value;
    }

    public void customize(String customizedDelimiter) {
        this.value = customizedDelimiter;
    }

    public String getValue() {
        return value;
    }
}
