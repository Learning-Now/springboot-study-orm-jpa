package week7.domain;

public class CustomizedDelimiter extends Delimiter{

    public CustomizedDelimiter(String customizedDelimiter) {
        value = customizedDelimiter;
    }

    public String[] split(String input) {
        return input.split(value);
    }
}
