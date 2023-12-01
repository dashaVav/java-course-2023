package edu.hw8.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHacking {
    public PasswordHacking(Map<String, String> map) {
        this.map = new ConcurrentHashMap<>(map);
    }

    public Map<String, String> map;

    public Map<String, String> decryptPasswords(int nTreads, int maxLenPassword) {
        ExecutorService threadPool = Executors.newFixedThreadPool(nTreads);

        for (int len = 1; len <= maxLenPassword; len++) {
            for (int i = 0; i < nTreads; i++) {
                int finalLen = len;
                threadPool.execute(() -> generateAndCheckPasswords(finalLen));
            }
        }
        threadPool.shutdown();
        return map;
    }

    private void generateAndCheckPasswords(int lenPassword) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(lenPassword);
        String password = passwordGenerator.nextPassword();
        while (password != null) {
            String hash = md5(password);
            if (map.containsKey(hash)) {
                map.put(password, map.get(hash));
            }
            password = passwordGenerator.nextPassword();
        }
    }

    private String md5(String password) {
        return DigestUtils.md5Hex(password);
    }

}
