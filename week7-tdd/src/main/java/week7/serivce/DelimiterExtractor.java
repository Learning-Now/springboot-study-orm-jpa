package week7.serivce;

public class DelimiterExtractor {

    private static final String OR_OPERATOR = "|";
    private static final StringBuilder DELIMITER = new StringBuilder(",|:");

    private DelimiterExtractor() {
    }

    public static String makeDelimiter(String data) {
        DELIMITER.append(OR_OPERATOR);
        DELIMITER.append(data);
        return DELIMITER.toString();
    }
}
