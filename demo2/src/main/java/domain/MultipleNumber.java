package domain;

import java.util.ArrayList;
import java.util.List;

public class MultipleNumber {

    public List<Number> numbers;

    public MultipleNumber(String[] numbers) {
        this(Converter.arrayToList(numbers));
    }

    public MultipleNumber(List<Number> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }
}
