package week7.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import week7.serivce.DelimiterExtractor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DelimiterExtractorTest {

    private static final String FIRST_SEPARATOR = "//";
    private static final String LAST_SEPARATOR = "\n";

    @DisplayName("구분자 추출 테스트")
    @Test
    public void makeDelimiterTest() {
        // given
        String input = "//;\n1,2,3";
        // when
        String delimiter = DelimiterExtractor.makeDelimiter(FIRST_SEPARATOR, LAST_SEPARATOR, input);
        // then
        assertThat(delimiter).isEqualTo(",|:|;");
    }
}
