package week7.serivce;

public class DelimiterExtractor {

    private static final String FIRST_SEPARATOR = "//";
    private static final String LAST_SEPARATOR = "\n";
    private static final String OR_OPERATOR = "|";
    private static String delimiter = ",|:";

    private DelimiterExtractor() {
    }

    public static String makeDelimiter(String data) {
        return delimiter + OR_OPERATOR + data.substring(data.indexOf(FIRST_SEPARATOR)+2, data.indexOf(LAST_SEPARATOR));
    }
}
