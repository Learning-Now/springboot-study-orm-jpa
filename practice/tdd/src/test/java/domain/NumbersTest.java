package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumbersTest {

    @DisplayName("numbers sum테스트")
    @Test
    void numbersSum() {
        //given
        String [] stringNumbers = {"1","2","3"};

        //when
        Numbers numbers = new Numbers(stringNumbers);
        int result = numbers.sum();

        //then
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("createNumbers 테스트")
    @ParameterizedTest
    @MethodSource(value = "generateData")
    void createNumber(List<Number> values) {
        assertThatThrownBy(() -> new Numbers(values))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Number(123),new Number(1),new Number(3))),
                Arguments.of(Arrays.asList(new Number(123),new Number("a"),new Number(3))),
                Arguments.of(Arrays.asList(new Number(123),new Number(-1),new Number(3))));
    }
}