package week7;

import week7.domain.*;
import week7.domain.Number;
import week7.serivce.DelimiterExtractor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewTddCalculator {

    private static final String FIRST_SEPARATOR = "//";
    private static final String LAST_SEPARATOR = "\n";
    private static final Delimiter DEFAULT_DELIMITER = new DefaultDelimiter();

    public int add(String data) {
        if (data.isEmpty()) {
            return 0;
        }
        if (data.startsWith(FIRST_SEPARATOR)) {
            CustomizedDelimiter customizedDelimiter = new CustomizedDelimiter(DelimiterExtractor.makeDelimiter(FIRST_SEPARATOR, LAST_SEPARATOR, data));
            data = data.substring(data.indexOf(LAST_SEPARATOR)+LAST_SEPARATOR.length());
            return new Numbers(toNumberList(customizedDelimiter.split(data))).sum();
        }
        return new Numbers(toNumberList(DEFAULT_DELIMITER.split(data))).sum();
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
                .mapToObj(Number::new)
                .collect(Collectors.toList());
    }
}
