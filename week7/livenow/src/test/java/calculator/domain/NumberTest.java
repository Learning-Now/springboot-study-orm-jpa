package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @Test
    void 숫자음수테스트() {
        Number number1 = new Number(10);
        Number number2 = new Number(-10);
    }

}