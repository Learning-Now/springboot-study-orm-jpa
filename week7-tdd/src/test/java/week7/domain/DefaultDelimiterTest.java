package week7.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DefaultDelimiterTest {

    private DefaultDelimiter defaultDelimiter;

    @BeforeEach
    void setUp() {
        defaultDelimiter = new DefaultDelimiter();
    }

    private static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of("1,2,3", new String[]{"1", "2", "3"}),
                Arguments.of("1:2:3", new String[]{"1", "2", "3"}),
                Arguments.of("1,2:3", new String[]{"1", "2", "3"}),
                Arguments.of("1:2,3", new String[]{"1", "2", "3"})
        );
    }

    @DisplayName("기본 구분자 생성 테스트")
    @Test
    public void createDefaultDelimiterTest() {
        // given
        // when
        new DefaultDelimiter();
        // then
    }

    @DisplayName("기본 구분자로 분리 테스트")
    @ParameterizedTest
    @MethodSource(value = "inputArguments")
    public void splitByDefaultDelimiterTest(String input, String[] expectedValue) {
        // given
        // when
        String[] result = defaultDelimiter.split(input);
        // then
        assertThat(result).isEqualTo(expectedValue);
    }
}
