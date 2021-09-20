package domain;

import util.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Numbers {
    private final List<Number> numbers;

    public Numbers(String[] numbers) {
        this(Converter.arrayToList(numbers));
    }

    public Numbers(List<Number> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers1 = (Numbers) o;
        return Objects.equals(numbers, numbers1.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
