package week7.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomizedDelimiterTest {

    private CustomizedDelimiter customizedDelimiter;

    @BeforeEach
    void setUp() {
        customizedDelimiter = new CustomizedDelimiter(",|:|;");
    }

    private static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of("1;2;3", new String[]{"1", "2", "3"}),
                Arguments.of("1,2;3", new String[]{"1", "2", "3"}),
                Arguments.of("1;2,3", new String[]{"1", "2", "3"}),
                Arguments.of("1:2;3", new String[]{"1", "2", "3"}),
                Arguments.of("1;2:3", new String[]{"1", "2", "3"})
        );
    }

    @DisplayName("지정 구분자 생성 테스트")
    @Test
    public void createCustomizedDelimiterTest() {
        // given
        // when
        new CustomizedDelimiter(",|:|;");
        // then
    }

    @DisplayName("지정 구분자로 분리 테스트")
    @ParameterizedTest
    @MethodSource(value = "inputArguments")
    public void splitByCustomizedDelimiterTest(String input, String[] expectedValue) {
        // given
        // when
        String[] result = customizedDelimiter.split(input);
        // then
        assertThat(result).isEqualTo(expectedValue);
    }
}
