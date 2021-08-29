package com.livenow.week6.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader BUFFEREDREADER = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {

    }

    public static String input() {
        try {
            return BUFFEREDREADER.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
