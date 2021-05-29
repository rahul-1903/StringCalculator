public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        else {
            return Integer.parseInt(numbers);
        }
    }
}
