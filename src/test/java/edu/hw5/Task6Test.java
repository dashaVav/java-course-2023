package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "abc, achfdbaabgabcaabg",
        "1a, 1a2b3c"
    })
    public void testSubsequenceTrueResult(String s, String t) {
        boolean result = Task6.subsequence(s, t);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
        "xyz, 1a2b3c",
        "abcd, achfdbaabgabcaabg",
        "123, 1a2b3c",
        "abc, bca"
    })
    public void testSubsequenceFalseResult(String s, String t) {
        boolean result = Task6.subsequence(s, t);
        assertFalse(result);
    }
}
