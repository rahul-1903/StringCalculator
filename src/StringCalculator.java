import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] tokens;
        if (numbers.startsWith("//")) {
            Matcher m = Pattern.compile("//(.+)\n(.*)").matcher(numbers);
            m.matches();
            String delimiter = m.group(1);
            numbers = m.group(2);
            tokens = numbers.split(delimiter);
        } else {
            tokens = numbers.split(",|\n");
        }

        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .sum();
        }
}
