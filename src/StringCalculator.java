import java.util.Arrays;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] tokens = numbers.split(",");

        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .sum();
        }
}
