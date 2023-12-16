package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

public final class Task2 {
    private Task2() {
    }

    public static void redefine() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtilsRedefine.class)
            .name(ArithmeticUtils.class.getName())
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    static class ArithmeticUtils {
        int sum(int a, int b) {
            return a + b;
        }
    }

    static class ArithmeticUtilsRedefine {
        int sum(int a, int b) {
            return a * b;
        }
    }
}



