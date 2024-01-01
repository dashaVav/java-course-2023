package edu.hw8;

import edu.hw8.task3.PasswordHacking;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1, warmups = 0)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 5)
public class Task3Benchmark {
    private static final Map<String, String> inputMap = Map.of(
        "202cb962ac59075b964b07152d234b70", "user1",
        "6d4db5ff0c117864a02827bad3c361b9", "user2",
        "ce827a5f1df5b82656d6ecebf1cd20ab", "user3"
    );

    @Benchmark
    public void singleThread(Blackhole blackhole) {
        PasswordHacking passwordHacking = new PasswordHacking(inputMap);
        blackhole.consume(passwordHacking.decryptPasswords(1, 4));
    }

    @Benchmark
    public void twoThreads(Blackhole blackhole) {
        PasswordHacking passwordHacking = new PasswordHacking(inputMap);
        blackhole.consume(passwordHacking.decryptPasswords(2, 4));
    }

    @Benchmark
    public void threeThreads(Blackhole blackhole) {
        PasswordHacking passwordHacking = new PasswordHacking(inputMap);
        blackhole.consume(passwordHacking.decryptPasswords(3, 4));
    }

    @Benchmark
    public void fourThreads(Blackhole blackhole) {
        PasswordHacking passwordHacking = new PasswordHacking(inputMap);
        blackhole.consume(passwordHacking.decryptPasswords(4, 4));
    }

    @Benchmark
    public void  eightThread(Blackhole blackhole) {
        PasswordHacking passwordHacking = new PasswordHacking(inputMap);
        blackhole.consume(passwordHacking.decryptPasswords(8, 4));
    }

}
