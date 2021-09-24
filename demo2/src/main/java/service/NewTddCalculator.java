package service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewTddCalculator {
    private static final String DEFAULT_DELIMETER = "[,;]";

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);

        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] tokens= m.group(2).split(customDelimiter);
            return sum(toInt(tokens));
        }
        return sum(toInt(split(input)));
    }

    private int sum(int[] toInt) {
        int value = 0;
        for(int numbers : toInt){
            value += numbers;
        }
        return value;
    }

    private String[] split(String input) {
        return input.split(DEFAULT_DELIMETER);
    }

    private int[] toInt(String[] input) {
        int[] numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = toInts(input[i]);
        }
        return numbers;
    }

    private int toInts(String number) {
        int number1 = Integer.parseInt(number);
        if (number1 < 0){
            throw new RuntimeException();
        }
        return number1;
    }

}
