package week7;

import java.util.Arrays;
import java.util.List;

public class NewTddCalculator {
    public int add(String data) {
        if (data.isEmpty()) {
            return 0;
        }
        List<String> numbers = Arrays.asList(data.split(",|:"));
        int result = 0;
        for (String number : numbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}
