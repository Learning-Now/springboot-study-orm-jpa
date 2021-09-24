package week7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TddCalculator {
    private static final String DEFAULT_DELIMITER = "[,;]";

    public int add(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        return sum(toInteger(split(text)));
    }

    private List<Integer> toInteger(List<String> values) {
        return values.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> split(String text) {
        return Arrays.stream(text.split(DEFAULT_DELIMITER))
                .collect(Collectors.toList());
    }

    private int sum(List<Integer> values) {
        return values.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
