package week7.domain;

public class Delimiter {

    private String value;

    public Delimiter() {
        this.value = ",|:";
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
