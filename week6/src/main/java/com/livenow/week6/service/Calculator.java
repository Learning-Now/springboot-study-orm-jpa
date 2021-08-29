package com.livenow.week6.service;

import com.livenow.week6.domain.Numbers;
import com.livenow.week6.view.InputView;
import com.livenow.week6.view.OutputView;

public class Calculator {

    public void start() {
        String input = InputView.input();
        Numbers numbers = Splitter.split(input);
        OutputView.output(numbers.add());
    }
}
