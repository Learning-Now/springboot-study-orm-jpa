package calculator.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Numbers {
    private final List<Number> numbers;

    public Numbers(List<String> stringNumberList) {
         numbers = stringNumberList.stream()
                 .map(number-> new Number(Integer.parseInt(number)))
                 .collect(Collectors.toList());
    }

    public int add() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }
}
