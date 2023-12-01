package edu.hw8;

import edu.hw8.task3.PasswordHacking;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private static final Map<String, String> inputMap = Map.of(
        "202cb962ac59075b964b07152d234b70", "user1",
        "6d4db5ff0c117864a02827bad3c361b9", "user2"
    );

    private static final Map<String, String> expectedResult = Map.of(
        "user1", "123",
        "user2", "moon"
    );

    @Test
    public void test() throws InterruptedException {
        PasswordHacking passwordHacking = new PasswordHacking(inputMap);
        Map<String, String> result = passwordHacking.decryptPasswords(4, 4);

        assertEquals(expectedResult, result);

    }
}
