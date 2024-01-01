package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import static java.lang.invoke.MethodHandles.lookup;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final int TIME_VALUE = 5;
    public static void main(String[] args)
        throws Throwable {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(TIME_VALUE))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(TIME_VALUE))
            .build();
        new Runner(options).run();
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private StudentNameGetter lambdaMetafactory;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod("name");
        methodHandle = lookup().findVirtual(Student.class, "name", MethodType.methodType(String.class));

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandleName = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));
        lambdaMetafactory = (StudentNameGetter) LambdaMetafactory.metafactory(
            lookup,
            "name",
            MethodType.methodType(StudentNameGetter.class),
            MethodType.methodType(String.class, Student.class),
            methodHandleName,
            MethodType.methodType(String.class, Student.class)
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflectMethod(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invokeExact(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaMetafactory.name(student);
        bh.consume(name);
    }
}

