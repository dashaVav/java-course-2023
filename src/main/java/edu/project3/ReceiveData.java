package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ReceiveData {
    private ReceiveData() {
    }

    private static final int TIME_OUT = 10;

    public static List<Log> request(URI uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .timeout(Duration.ofSeconds(TIME_OUT))
            .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Arrays.stream(response.body().split("\n")).map(LogParser::parse).collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    public static List<Log> read(Path path) {
        try {
            List<String> logList = Files.readAllLines(path);
            return logList.stream().map(LogParser::parse).collect(Collectors.toList());
        } catch (IOException e) {
            return List.of();
        }
    }
}
