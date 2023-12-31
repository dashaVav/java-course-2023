package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {
    private Task5() {
    }

    private static HttpClient client = HttpClient.newHttpClient();
    private static final int TIME_OUT = 10;
    private static final HttpRequest REQUEST = request();

    private static long[] stringToLongArray(String str) {
        if (str.isEmpty()) {
            return new long[0];
        }

        return Arrays.stream(str.replaceAll("[\\[\\]\"]", "").split(","))
            .mapToLong(Long::parseLong)
            .toArray();
    }

    private static HttpRequest request() {
        return HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .timeout(Duration.ofSeconds(TIME_OUT))
            .build();
    }

    public static long[] hackerNewsTopStories() {
        try {
            var response = client.send(REQUEST, HttpResponse.BodyHandlers.ofString());
            return stringToLongArray(response.body());
        } catch (Exception e) {
            return new long[0];
        }
    }

    private static String titleFromResponseBody(String str) {
        Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1) : "Unknown News";
    }

    public static String news(long id) {
        try {
            var response = client.send(REQUEST, HttpResponse.BodyHandlers.ofString());
            return titleFromResponseBody(response.body());
        } catch (Exception e) {
            return titleFromResponseBody("");
        }
    }

    public static void setClient(HttpClient httpClient) {
        client = httpClient;
    }
}
