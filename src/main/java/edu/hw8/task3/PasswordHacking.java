package edu.hw8.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHacking {
    public PasswordHacking(Map<String, String> map) {
        this.passwordLoginMap = new ConcurrentHashMap<>(map);
        this.loginPasswordMap = new ConcurrentHashMap<>();
    }

    private final Map<String, String> passwordLoginMap;
    private final Map<String, String> loginPasswordMap;

    public Map<String, String> decryptPasswords(int nTreads, int maxLenPassword) {
        ExecutorService threadPool = Executors.newFixedThreadPool(nTreads);
        for (int len = 1; len <= maxLenPassword; len++) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(len);
            for (int i = 0; i <= nTreads; i++) {
                threadPool.execute(() -> generateAndCheckPasswords(passwordGenerator));
            }
        }

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return loginPasswordMap;
    }

    private void generateAndCheckPasswords(PasswordGenerator passwordGenerator) {

        String password = passwordGenerator.nextPassword();
        while (password != null) {
            if (passwordLoginMap.isEmpty()) {
                return;
            }

            String hash = md5(password);
            if (passwordLoginMap.containsKey(hash)) {
                loginPasswordMap.put(passwordLoginMap.get(hash), password);
                passwordLoginMap.remove(hash);
            }
            password = passwordGenerator.nextPassword();
        }
    }

    private String md5(String password) {
        return DigestUtils.md5Hex(password);
    }

}
