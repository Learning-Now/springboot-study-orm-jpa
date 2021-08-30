package service;

import java.util.Arrays;

public class StringCalculator {

    public StringCalculator() {
    }

    public int add(String input) {
        if (input == ""){
            return 0;
        }
        return sum(split(input));
    }

    private String[] split(String input) {
        String[] numbers = input.split("[,;]");
        return numbers;
    }

    private int sum(String[] numbers) {
        return Arrays.stream(numbers)
                .mapToInt(number -> Integer.parseInt(number))
                .sum();
    }
}
