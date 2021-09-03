package week7;

import java.util.Arrays;
import java.util.List;

public class NewTddCalculator {

    public int add(String data) {
        if (data.isEmpty()) {
            return 0;
        }
        String delimiter = ",|:";
        if (data.startsWith("//")) {
            delimiter += "|" + data.substring(data.indexOf("//")+2, data.indexOf("\n"));
            data = data.substring(data.indexOf("\n")+1);
        }
        List<String> numbers = Arrays.asList(data.split(delimiter));
        int result = 0;
        for (String number : numbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}
