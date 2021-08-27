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
        String inputString1 = "1,2";
        String inputString2 = "//;\n1;2;3";
        CalculatorService calculatorService = new CalculatorService();
        assertThat(calculatorService.checkCustomDelimiter(inputString2)).isEqualTo(true);
    }

    @Test
    void checkValidateHaveDelimiter1() throws IllegalArgumentException {
        String inputString1 = "123";
        String inputString2 = "//;\n1;2;3";
        CalculatorService calculatorService = new CalculatorService();
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString2);
        });
    }

    @Test
    void checkValidateHaveDelimiter2() throws IllegalArgumentException{
        String inputString1 = "1,23";
        String inputString2 = "//;\n1;2;3";
        CalculatorService calculatorService = new CalculatorService();
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString1);
        });
    }

    @Test
    void checkValidateHaveDelimiter3() throws IllegalArgumentException{
        String inputString1 = "1,2;3";
        String inputString2 = "//;\n1;2;3";
        CalculatorService calculatorService = new CalculatorService();
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString1);
        });
    }

    @Test
    void checkValidateHaveDelimiter4() throws IllegalArgumentException{
        String inputString1 = "123";
        String inputString2 = "//;\n1;2;3";
        CalculatorService calculatorService = new CalculatorService();
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.stringCalculator(inputString1);
        });
    }
}