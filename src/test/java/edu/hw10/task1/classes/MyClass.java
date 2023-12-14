package edu.hw10.task1.classes;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import lombok.Getter;

@Getter
public class MyClass {
    @Min(10) @Max(13) private final int intValue;
    @Max(1000) private final double doubleValue;

    public MyClass(int intValue, double doubleValue) {
        this.intValue = intValue;
        this.doubleValue = doubleValue;
    }

    public static MyClass create(int intValue, double doubleValue) {
        return new MyClass(intValue, doubleValue);
    }

    @Override
    public String toString() {
        return String.format("MyClass[intValue=%d, doubleValue=%f]", intValue, doubleValue);
    }
}
