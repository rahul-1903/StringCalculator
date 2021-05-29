import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    public static int Add(String numbers) {

        String[] tokens = getTokens(numbers);
        List<String> negatives = getNegatives(tokens);
        if (negatives.size() > 0) {
            throw new RuntimeException("negatives not allowed: " + negatives.stream().collect(Collectors.joining(",")));
        }
        return getSum(tokens);
    }

    private static List<String> getNegatives(String[] tokens) {
        return Arrays.stream(tokens)
                .filter(elem -> Integer.parseInt(elem) < 0)
                .collect(Collectors.toList());
    }

    private static String[] getTokens(String numbers) {
        if (numbers.isEmpty()) {
            return new String[0];
        }
        if (numbers.startsWith("//")) {
            return getTokensByCustomDelimiter(numbers);
        } else {
            return getTokensByDefaultDelimiter(numbers);
        }
    }

    private static String[] getTokensByDefaultDelimiter(String numbers) {
        return numbers.split(",|\n");
    }

    private static String[] getTokensByCustomDelimiter(String numbers) {
        Matcher m = Pattern.compile("//(.+)\n(.*)").matcher(numbers);
        m.matches();
        String delimiter = getDelimiterFromMatches(m);
        numbers = m.group(2);
        return numbers.split(delimiter);
    }

    private static String getDelimiterFromMatches(Matcher m) {
        String delimiter = Pattern.quote(m.group(1));

        List<String> delimiterArray = new ArrayList<>();
        Matcher nm = Pattern.compile("\\[(.+?)\\]").matcher(delimiter);
        while (nm.find()) {
            delimiterArray.add(nm.group(1));
        }
        if (delimiterArray.size() > 0) {
            delimiter = "";
        }
        for (int i = 0; i < delimiterArray.size(); i++) {
            delimiter += Pattern.quote(delimiterArray.get(i));
            if (i != delimiterArray.size() - 1) {
                delimiter += "|";
            }
        }
        return delimiter;
    }

    private static int getSum(String[] tokens) {
        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .filter(elem -> elem <= 1000)
                .sum();
    }
}
