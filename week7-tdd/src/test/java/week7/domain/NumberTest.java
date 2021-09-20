package week7.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class NumberTest {

    private static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of(3, 3),
                Arguments.of(5, 5),
                Arguments.of(20, 20)
        );
    }

    @DisplayName("int 생성자 테스트")
    @ParameterizedTest
    @MethodSource(value = "inputArguments")
    public void intValueConstructorTest(int input, int expectedValue) {
        // given
        // when
        Number number = new Number(input);
        int result = number.getValue();
        // then
        assertThat(result).isEqualTo(expectedValue);
    }

    @DisplayName("String 생성자 테스트")
    @Test
    public void StringValueConstructorTest() {
        // given
        String input = "1";
        // when
        Number number = new Number(input);
        int result = number.getValue();
        // then
        assertThat(result).isEqualTo(1);
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
