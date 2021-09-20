package week7.util;

import week7.domain.Number;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    private Converter() {
    }

    public static int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자 이외의 값이 포함되어 있습니다.");
        }
    }

    public static List<Number> toNumberList(String[] values) {
        return Arrays.stream(values)
                .mapToInt(Converter::toInt)
                .mapToObj(Number::new)
                .collect(Collectors.toList());
    }
}
