package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    public void testMethodToString() {
        try {
            var instance = new ByteBuddy().subclass(Object.class)
                .method(named("toString")).intercept(FixedValue.value("Hello, ByteBuddy!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance();
            assertEquals("Hello, ByteBuddy!", instance.toString());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
