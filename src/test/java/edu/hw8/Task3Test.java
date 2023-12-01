package edu.hw8;

import edu.hw8.task3.PasswordHacking;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class Task3Test {
    @Test
    public void test() throws InterruptedException {
        Map<String, String> map = Map.of(
           "47bce5c74f589f4867dbd57e9ca9f808", "aaa"
        );
        PasswordHacking passwordHacking = new PasswordHacking(map);
        passwordHacking.decryptPasswords(5, 3);

    }

}
