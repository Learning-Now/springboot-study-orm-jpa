package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {

    @DisplayName("Numbers 카운트 테스")
    @Test
    void numbersCount() {
        //given
        String [] stringNumbers = {"1","2","3"};

        //when
        Numbers numbers = new Numbers(stringNumbers);
        //then
        assertThat(numbers.count()).isEqualTo(3);
    }
}