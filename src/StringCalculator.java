public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if(numbers.contains(",")) {
            String[] tokens = numbers.split(",");
            return Integer.parseInt(tokens[0]) + Integer.parseInt(tokens[1]);
        }
        else {
            return Integer.parseInt(numbers);
        }
    }
}
