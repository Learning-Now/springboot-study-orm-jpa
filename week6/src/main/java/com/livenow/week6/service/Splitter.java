package com.livenow.week6.service;

import com.livenow.week6.domain.CustomDelimiter;
import com.livenow.week6.domain.Delimiter;
import com.livenow.week6.domain.Number;
import com.livenow.week6.domain.Numbers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Splitter {

    private static final String SEPARATOR_START = "//";
    private static final String SEPARATOR_END = "\\n";
    private static String delimiter = new Delimiter().getValue();

    public static Numbers split(String formula) {
        if (formula.contains(SEPARATOR_START) && formula.contains(SEPARATOR_END)) {
            delimiter = DelimiterExtractor.creatCustomDelimiter(formula).getValue();
            formula = formula.substring(5);
        }
        List<Number> numbers = Arrays.stream(formula.split(delimiter))
                .map(Number::new)
                .collect(Collectors.toList());
        return new Numbers(numbers);
    }
}
