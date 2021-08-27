package calculator.domain;

public class Delimiter {
    private final String value;

    public Delimiter(String value) {
        this.value = value;
    }

    public String[] split(String bufferInput) {
        return bufferInput.split(value);
    }
}
