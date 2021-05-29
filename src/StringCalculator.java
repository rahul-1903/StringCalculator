import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] tokens = getTokens(numbers);
        List<String> negatives = Arrays.stream(tokens)
                .filter(elem -> Integer.parseInt(elem) < 0)
                .collect(Collectors.toList());
        if (negatives.size() > 0) {
            throw new RuntimeException("negatives not allowed: " + negatives.stream().collect(Collectors.joining(",")));
        }
        return getSum(tokens);
    }

    private static String[] getTokens(String numbers) {
        String[] tokens;
        if (numbers.startsWith("//")) {
            tokens = getTokensByCustomDelimiter(numbers);
        } else {
            tokens = getTokensByDefaultDelimiter(numbers);
        }
        return tokens;
    }

    private static String[] getTokensByDefaultDelimiter(String numbers) {
        return numbers.split(",|\n");
    }

    private static String[] getTokensByCustomDelimiter(String numbers) {
        String[] tokens;
        Matcher m = Pattern.compile("//(.+)\n(.*)").matcher(numbers);
        m.matches();
        String delimiter = Pattern.quote(m.group(1));

        List<String> delimiterArray = new ArrayList<>();
        Matcher nm = Pattern.compile("\\[(.+?)\\]").matcher(delimiter);
        while (nm.find()) {
            delimiterArray.add(nm.group(1));
        }
        if (delimiterArray.size() > 0)
            delimiter = "";
        for (int i=0; i<delimiterArray.size(); i++) {
            delimiter += Pattern.quote(delimiterArray.get(i));
            if (i != delimiterArray.size() - 1)
                delimiter += "|";
        }
        numbers = m.group(2);
        tokens = numbers.split(delimiter);
        return tokens;
    }

    private static int getSum(String[] tokens) {
        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .filter(elem -> elem <= 1000)
                .sum();
    }
}
