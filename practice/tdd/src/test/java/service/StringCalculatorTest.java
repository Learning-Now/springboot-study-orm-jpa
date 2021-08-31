package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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

    @DisplayName("1,2,3 입력 받았을 경우 테스트")
    @Test
    void inputMultipleNumbersWithPattern() {
        //given
        String input = "1,2,3";
        //when
        int result = stringCalculator.add(input);
        //then
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("1,2;3 입력 받았을 경우 테스트")
    @Test
    void inputMultipleNumbersWithDefaultPattern() {
        //given
        String input = "1,2;3";
        //when
        int result = stringCalculator.add(input);
        //then
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("커스텀 구분자 인식하기")
    @Test
    void inputCustomDelimiter() {
        //given
        String input = "//!\n1";
        //when
        int result = stringCalculator.add(input);
        //then
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("커스텀 구분자로 복수의 수 연산하기")
    @Test
    void inputCustomDelimiterWithMultipleNumbers() {
        //given
        String input = "//!\n1!2!3";
        //when
        int result = stringCalculator.add(input);
        //then
        assertThat(result).isEqualTo(6);
    }
}