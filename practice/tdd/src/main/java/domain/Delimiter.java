package domain;

public abstract class Delimiter {
    private static final String DEFAULT_PATTERN = "[,;]";
    protected final String value = DEFAULT_PATTERN;

    public abstract String[] split(String input);
}
