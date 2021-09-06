package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Numbers {
    private final List<Number> numbers;

    public Numbers(String[] stringArrayNumbers) {
        this(arrayToList(stringArrayNumbers));
    }

    public Numbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }

    private static List<Number> arrayToList(String[] stringNumbers) {
        return Arrays.stream(stringNumbers)
                .map(stringNumber -> new Number(stringNumber))
                .collect(Collectors.toList());
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
