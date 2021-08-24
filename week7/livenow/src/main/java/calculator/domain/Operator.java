package calculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Operator {
    private static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/", ",", ";");
    private final String value;

    public Operator(String value) {
        validateOperator(value);
        this.value = value;
    }

    public void validateOperator(String value) {
        if (!OPERATORS.contains(value)){
            throw new IllegalArgumentException("Error : 잘못된 연산자 입력입니다.");
        }
    }
}
