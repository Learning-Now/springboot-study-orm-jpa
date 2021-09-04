package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @DisplayName("validate 사용한 숫자 타입 검증")
    @Test
    void validateNumber3() throws IllegalArgumentException {
        //given
        String input = "12a";
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            Number number = new Number(input);
        });
    }
}