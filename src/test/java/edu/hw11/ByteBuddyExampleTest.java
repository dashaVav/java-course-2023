package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyExampleTest {
    @Test
    public void testTask1() {
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

    @Test
    public void testTask2() {
        Task2.redefine();
        Task2.ArithmeticUtils arithmeticUtils = new Task2.ArithmeticUtils();
        int actualResult = arithmeticUtils.sum(10, 10);
        assertEquals(100, actualResult);
    }

    @Test
    public void testTask3() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Object clazz = Task3.returnClass();
        long result = (long) clazz.getClass().getMethod("fib", int.class).invoke(clazz, 10);
        assertEquals(55, result);
    }
}
