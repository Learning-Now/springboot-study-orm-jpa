package com.livenow.week6.service;

import com.livenow.week6.domain.CustomDelimiter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DelimiterExtractor {

    private static final String SEPARATOR_START = "//";
    private static final String SEPARATOR_END = "\\n";

    public static CustomDelimiter creatCustomDelimiter(String value) {
        String customDelimiterStart = value.substring(0, 2);
        String customDelimiterEnd = value.substring(3, 5);
        if (!customDelimiterStart.equals(SEPARATOR_START) || !customDelimiterEnd.equals(SEPARATOR_END)) {
            throw new RuntimeException();
        }
        return new CustomDelimiter(value.substring(2, 3));
    }
}
