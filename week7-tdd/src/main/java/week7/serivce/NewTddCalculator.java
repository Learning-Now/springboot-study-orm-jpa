package week7.serivce;

import week7.domain.*;
import week7.domain.Number;
import week7.serivce.DelimiterExtractor;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NewTddCalculator {

    private static final int CUSTOM_DELIMITER_LOCATION = 1;
    private static final String CUSTOM_PATTERN = "//(.)\n";
    private static final Delimiter DEFAULT_DELIMITER = new DefaultDelimiter();
    private static final Pattern PATTERN = Pattern.compile(CUSTOM_PATTERN);

    public int add(String data) {
        if (data.isEmpty()) {
            return 0;
        }
        Matcher matcher = PATTERN.matcher(data);
        if (matcher.lookingAt()) {
            Delimiter customizedDelimiter = new CustomizedDelimiter(DelimiterExtractor.makeDelimiter(matcher.group(CUSTOM_DELIMITER_LOCATION)));
            return new Numbers(toNumberList(customizedDelimiter.split(data.substring(matcher.end())))).sum();
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
