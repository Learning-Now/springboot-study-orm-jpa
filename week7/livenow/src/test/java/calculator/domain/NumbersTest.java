package calculator.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {

    @Test
    void Numbers_생성_add_테스트() {
        //given
        List<String> intNumbers = new ArrayList<>();
        intNumbers.add("1");
        intNumbers.add("2");

        //when
        Numbers numbers = new Numbers(intNumbers);

        //then
        assertThat(numbers.add()).isEqualTo(3);

    }
}