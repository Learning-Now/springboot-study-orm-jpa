package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumbersTest {

    @DisplayName("createNumbers 테스트")
    @Test
    void createArrayNumberTest() {
        //given
        String [] arrayNumbers = {"1","2","3"};
        //when
        //then
        assertThat(new Numbers(arrayNumbers)).isEqualTo(new Numbers(new String[]{"1", "2", "3"}));
    }

    @DisplayName("createNumbers 테스트")
    @Test
    void createListNumberTest() {
        //given
        List<Number> listNumbers = Arrays.asList(new Number(1), new Number(2), new Number(3));
        //when
        //then
        assertThat(new Numbers(listNumbers)).isEqualTo(new Numbers(Arrays.asList(new Number(1), new Number(2), new Number(3))));
    }

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