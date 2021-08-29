package com.livenow.week6.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputView {

    private static final BufferedReader BUFFEREDREADER = new BufferedReader(new InputStreamReader(System.in));

    public static String input() {
        try {
            return BUFFEREDREADER.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
