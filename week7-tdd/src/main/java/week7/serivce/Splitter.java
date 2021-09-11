package week7.serivce;

import week7.domain.Delimiter;

public class Splitter {

    private Splitter() {
    }

    public static String[] split(String data, Delimiter delimiter) {
        return data.split(delimiter.getValue());
    }
}
