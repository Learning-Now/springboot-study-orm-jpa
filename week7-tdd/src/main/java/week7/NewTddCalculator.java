package week7;

import week7.domain.Number;
import week7.domain.Numbers;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        Numbers numbers = new Numbers(Arrays.stream(data.split(delimiter))
                .map(Number::new)
                .collect(Collectors.toList()));
        return numbers.sum();
    }
}
