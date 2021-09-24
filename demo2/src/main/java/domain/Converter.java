package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    private Converter() {

    }

    public int toInteger(String value){
        return Integer.parseInt(value);
    }

    public static List<Number> arrayToList(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Number::new)
                .collect(Collectors.toList());
    }
}
