package week7;

import week7.domain.Delimiter;
import week7.domain.Number;
import week7.domain.Numbers;
import week7.serivce.DelimiterExtractor;

import java.util.Arrays;
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
            DELIMITER.customize(DelimiterExtractor.makeDelimiter(data));
            data = data.substring(data.indexOf(LAST_SEPARATOR)+1);
        }
        Numbers numbers = new Numbers(Arrays.stream(data.split(DELIMITER.getValue()))
                .map(Number::new)
                .collect(Collectors.toList()));
        return numbers.sum();
    }
}
