package domain;

import java.util.Objects;

public class CustomDelimiter extends Delimiter{

    public CustomDelimiter(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomDelimiter that = (CustomDelimiter) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
