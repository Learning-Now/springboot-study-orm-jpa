package week7.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberTest {

    private static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of(3, 3),
                Arguments.of(5, 5),
                Arguments.of(20, 20)
        );
    }

    @DisplayName("생성 테스트")
    @ParameterizedTest
    @MethodSource(value = "inputArguments")
    public void createNumberTest(int input, int expectedValue) {
        // given
        // when
        Number number = new Number(input);
        int result = number.getValue();
        // then
        assertThat(result).isEqualTo(expectedValue);
    }

    @DisplayName("음수 입력 시 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, -20})
    public void inputNegativeNumberTest(int input) {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            new Number(input);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("입력 값에 음수가 포함되어 있습니다.");
    }
}
