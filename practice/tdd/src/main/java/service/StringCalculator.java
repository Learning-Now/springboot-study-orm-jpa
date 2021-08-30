package service;

import java.util.Arrays;

public class StringCalculator {

    public StringCalculator() {
    }

    public int add(String input) {
        if (input.contains(",")) {
            String[] numbers = input.split(",");
            return sum(numbers);
        }
        return Integer.parseInt(input);
    }

    private int sum(String[] numbers) {
        return Arrays.stream(numbers)
                .mapToInt(number -> Integer.parseInt(number))
                .sum();
    }
}
