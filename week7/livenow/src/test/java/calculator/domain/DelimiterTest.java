package calculator.domain;

import calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DelimiterTest {

    @Test
    void splitTest() {
        //given
        String inputString1 = "123";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        calculatorService.stringCalculator(inputString2);
    }

}