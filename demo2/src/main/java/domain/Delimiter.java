package domain;

public class Delimiter{

    protected String value;

    public String[] split(String value) {
        return value.split(value);
    }

    public String getValue() {
        return value;
    }
}
