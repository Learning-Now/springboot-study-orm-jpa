package com.livenow.week6.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OutputView {

    public static void output(int value) {
        System.out.println(value);
    }
}
