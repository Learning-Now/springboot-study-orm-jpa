package week7.serivce;

public class DelimiterExtractor {

    private static final int FIRST_SEPARATOR_LENGTH = 2;
    private static final String OR_OPERATOR = "|";
    private static final StringBuilder DELIMITER = new StringBuilder(",|:");

    private DelimiterExtractor() {
    }

    public static String makeDelimiter(String firstSeparator, String lastSeparator, String data) {
        DELIMITER.append(OR_OPERATOR);
        DELIMITER.append(data, data.indexOf(firstSeparator)+FIRST_SEPARATOR_LENGTH, data.indexOf(lastSeparator));
        return DELIMITER.toString();
    }
}
