import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    public void addNumberInEmptyString() {
        assertEquals(0, StringCalculator.Add(""));
    }
}