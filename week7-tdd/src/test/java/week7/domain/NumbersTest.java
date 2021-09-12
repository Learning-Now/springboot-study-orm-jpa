package week7.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumbersTest {

    private static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Number(1), new Number(2), new Number(3)), 6),
                Arguments.of(Arrays.asList(new Number(12), new Number(17), new Number(8)), 37)
        );
    }

    @DisplayName("sum 테스트")
    @ParameterizedTest
    @MethodSource(value = "inputArguments")
    public void sumNumbersTest(List<Number> numberList, int expectedValue) {
        // given
        // when
        Numbers numbers = new Numbers(numberList);
        int result = numbers.sum();
        // then
        assertThat(result).isEqualTo(expectedValue);
    }
}
