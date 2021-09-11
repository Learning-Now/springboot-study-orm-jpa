package week7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class NewTddCalculatorTest {

    private NewTddCalculator newTddCalculator;

    @BeforeEach
    void setUp() {
        newTddCalculator = new NewTddCalculator();
    }

    private static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of("3", 3),
                Arguments.of("", 0),
                Arguments.of("2,3", 5),
                Arguments.of("2:3", 5),
                Arguments.of("2,3,4:5", 14),
                Arguments.of("//;\n1;2;3", 6)
        );
    }

    @DisplayName("입력 테스트")
    @ParameterizedTest
    @MethodSource(value = "inputArguments")
    public void inputEmptyDataTest() {
        //given
        String data = "";
        // when
        int result = newTddCalculator.add(data);
        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("숫자 이외의 값 전달 시 예외 발생")
    public void makeNoNumberErrorTest() {
        //given
        String data = "a,1,2";
        // when
        // then
        assertThatThrownBy(() -> {
            newTddCalculator.add(data);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("숫자 이외의 값이 포함되어 있습니다.");
    }

    @Test
    @DisplayName("음수 값 전달 시 예외 발생")
    public void makeNegativeNumberErrorTest() {
        //given
        String data = "-1,1,2";
        // when
        // then
        assertThatThrownBy(() -> {
            newTddCalculator.add(data);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("입력 값에 음수가 포함되어 있습니다.");
    }
}
