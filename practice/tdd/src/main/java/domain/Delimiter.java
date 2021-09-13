package domain;

import java.util.Objects;

public class Delimiter {
    private final String value;

    public Delimiter(String value) {
        this.value = value;
    }

    public String[] split(String input) {
        return input.split(this.value);
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
