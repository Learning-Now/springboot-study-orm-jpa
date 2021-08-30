package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @DisplayName("아무것도 입력받지 않았을때 테스트")
    @Test
    void 아무것도입력받지않았을때() {
        //given
        String input = "";
        //when
        int result = stringCalculator.add(input);
        //then
        assertThat(result).isEqualTo(0);
    }

    @DisplayName("1,2 입력받았을경우 테스트")
    @Test
    void inputDualNumberWithPattern() {
        //given
        String input = "1,2";
        //when
        int result = stringCalculator.add(input);
        //then
        assertThat(result).isEqualTo(3);
    }
}