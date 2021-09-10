package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @DisplayName("createNumber Test")
    @Test
    void createNumberTest() {
        //given
        String input = "12";
        Number number = new Number(input);
        //when
        //then
        assertThat(number).isEqualTo(new Number("12"));
    }

    @DisplayName("createNumberFail 반복 테스트")
    @ParameterizedTest
    @CsvSource({"12a", "-1"}) //단 1개의 타입만 넘겨야함.
    void createNumberFailTest(String value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}