package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    @Test
    void 연산자체크() {
        Operator operator = new Operator("+");
        operator.validateOperator("%");
    }
}