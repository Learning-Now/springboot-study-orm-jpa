package week7.serivce;

import week7.domain.CustomizedDelimiter;
import week7.domain.DefaultDelimiter;
import week7.domain.Delimiter;
import week7.domain.Numbers;
import week7.util.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            return new Numbers(Converter.toNumberList(customizedDelimiter.split(data.substring(matcher.end())))).sum();
        }
        return new Numbers(Converter.toNumberList(DEFAULT_DELIMITER.split(data))).sum();
    }
}
