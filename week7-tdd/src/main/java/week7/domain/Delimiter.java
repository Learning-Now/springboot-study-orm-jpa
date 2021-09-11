package week7.domain;

public abstract class Delimiter {

    protected String value;

    public abstract String[] split(String input);
}
