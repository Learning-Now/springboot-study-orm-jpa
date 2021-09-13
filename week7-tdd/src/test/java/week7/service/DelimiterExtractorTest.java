package week7.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import week7.serivce.DelimiterExtractor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DelimiterExtractorTest {

    @DisplayName("구분자 추출 테스트")
    @Test
    public void makeDelimiterTest() {
        // given
        String input = ";";
        // when
        String delimiter = DelimiterExtractor.makeDelimiter(input);
        // then
        assertThat(delimiter).isEqualTo(",|:|;");
    }
}
