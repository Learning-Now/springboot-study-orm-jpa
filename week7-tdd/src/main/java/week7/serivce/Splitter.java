package week7.serivce;

import week7.domain.Delimiter;
import week7.domain.Number;
import week7.domain.Numbers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Splitter {

    private Splitter() {
    }

    public static Numbers split(String data, Delimiter delimiter) {
        return new Numbers(Arrays.stream(data.split(delimiter.getValue()))
                .map(Number::new)
                .collect(Collectors.toList()));
    }
}
