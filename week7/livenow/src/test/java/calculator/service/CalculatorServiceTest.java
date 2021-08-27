package calculator.service;

import calculator.domain.Delimiter;
import calculator.domain.Operator;
import calculator.util.Input;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {

    @Test
    void checkNull() throws IllegalArgumentException {
        //given
        /*CalculatorService calculatorService = new CalculatorService();
        String inputString1 = null;
        String inputString2 = "";
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.validateNull(inputString1);
            calculatorService.validateNull(inputString2);
        });*/
    }

    @Test
    void checkCustomDelimiter() {
        //given
        String inputString1 = "1,2";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        assertThat(calculatorService.checkCustomDelimiter(inputString2)).isEqualTo(true);
    }

    @Test
    void checkValidateHaveDelimiter1() throws IllegalArgumentException {
        //given
        String inputString1 = "123";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString2);
        });
    }

    @Test
    void checkValidateHaveDelimiter2() throws IllegalArgumentException{
        //given
        String inputString1 = "1,23";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString1);
        });
    }

    @Test
    void checkValidateHaveDelimiter3() throws IllegalArgumentException{
        //given
        String inputString1 = "1,2;3";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString1);
        });
    }

    @Test
    void checkValidateHaveDelimiter4() throws IllegalArgumentException{
        //given
        String inputString1 = "123";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString1);
        });
    }

    @Test
    void getCalculateResult() {
        //given
        String inputString1 = "123";
        String inputString2 = "//;\n1;2;3";
        //when
        CalculatorService calculatorService = new CalculatorService();
        //then
        System.out.println(calculatorService.stringCalculator(inputString2));
    }
}