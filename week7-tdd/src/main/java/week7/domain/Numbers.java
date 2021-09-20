package week7.domain;

import week7.util.Converter;

import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private List<Number> numbers;

    public Numbers(List<Number> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public Numbers(String[] input) {
        this(Converter.toNumberList(input));
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }
}
