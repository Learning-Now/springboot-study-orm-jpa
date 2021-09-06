package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

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
}