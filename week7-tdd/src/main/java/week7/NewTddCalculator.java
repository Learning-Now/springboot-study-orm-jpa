package week7;

import week7.domain.Delimiter;
import week7.domain.Number;
import week7.domain.Numbers;
import week7.serivce.DelimiterExtractor;
import week7.serivce.Splitter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewTddCalculator {

    private static final String FIRST_SEPARATOR = "//";
    private static final String LAST_SEPARATOR = "\n";
    private static final Delimiter DELIMITER = new Delimiter();

    public int add(String data) {
        if (data.isEmpty()) {
            return 0;
        }
        if (data.startsWith(FIRST_SEPARATOR)) {
            DELIMITER.customize(DelimiterExtractor.makeDelimiter(FIRST_SEPARATOR, LAST_SEPARATOR, data));
            data = data.substring(data.indexOf(LAST_SEPARATOR)+LAST_SEPARATOR.length());
        }
        Numbers numbers = new Numbers(toNumberList(Splitter.split(data, DELIMITER)));
        return numbers.sum();
    }

    private int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자 이외의 값이 포함되어 있습니다.");
        }
    }

    private List<Number> toNumberList(String[] values) {
        return Arrays.stream(values)
                .mapToInt(this::toInt)
                .mapToObj(Number::new
                ).collect(Collectors.toList());
    }
}
