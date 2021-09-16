package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @DisplayName("createNumber Test")
    @ParameterizedTest
    @ValueSource(strings = {"1","2","34"})
    void createNumberTestByString(String value) {
        //given
        Number number = new Number(value);
        //when
        //then
        assertThat(number).isEqualTo(new Number(value));

    }

    @DisplayName("createNumber Test")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void createNumberTestByInt(int value) {
        //given
        Number number = new Number(value);
        //when
        //then
        assertThat(number).isEqualTo(new Number(value));

    }

    @DisplayName("NumberExceptionByInteger Test")
    @ParameterizedTest
    @MethodSource("generateDataByString")
    void NumberExceptionTest(String value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("NumberExceptionByString Test")
    @ParameterizedTest
    @MethodSource("generateDataByInt")
    void NumberExceptionTest(int value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(RuntimeException.class);
    }

    static Stream<Arguments> generateDataByInt() {
        return Stream.of(
                Arguments.of(-2),
                Arguments.of(-1));
    }

    static Stream<Arguments> generateDataByString() {
        return Stream.of(
                Arguments.of("a"),
                Arguments.of("마"),
                Arguments.of(" "));
    }
}