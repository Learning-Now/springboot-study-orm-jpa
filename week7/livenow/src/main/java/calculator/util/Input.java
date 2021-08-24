package calculator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    public static String BufferInput() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            return buffer.readLine();
        } catch (IOException e){
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

}
