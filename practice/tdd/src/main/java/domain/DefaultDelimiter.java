package domain;

import java.util.Objects;

public class DefaultDelimiter extends Delimiter{
    private static final String DEFAULT_PATTERN = "[,;]";

    public DefaultDelimiter() {
        super.value = DEFAULT_PATTERN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultDelimiter delimiter = (DefaultDelimiter) o;
        return Objects.equals(value, delimiter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
