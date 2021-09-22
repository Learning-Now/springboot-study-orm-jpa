package domain;

public abstract class Delimiter {
    protected String value;

    public String[] split(String input) {
        return input.split(value);
    }
}
