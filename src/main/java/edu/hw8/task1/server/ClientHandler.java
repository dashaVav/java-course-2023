package edu.hw8.task1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private final static List<String> PHRASES = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления",
        "Я бы попытался объяснить тебе, но сомневаюсь, что ты справишься с таким объемом информации.",
        "Чтобы мы разговаривали на одном уровне, мне нужно будет лечь."
    );

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String word = in.readLine();
            try (var writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                writer.println(getPhrase(word));
            }
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPhrase(String word) {
        String wordForSearch = word.replace("\n", "");
        for (String phase : PHRASES) {
            if (phase.toLowerCase().contains(wordForSearch.toLowerCase())) {
                return phase;
            }
        }
        return PHRASES.get(ThreadLocalRandom.current().nextInt(0, PHRASES.size()));
    }
}
