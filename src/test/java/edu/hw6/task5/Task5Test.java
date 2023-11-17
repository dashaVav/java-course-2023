package edu.hw6.task5;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class Task5Test {

    private HttpClient mockHttpClient;

    @BeforeEach
    void setUp() {
        mockHttpClient = Mockito.mock(HttpClient.class);
        Task5.setClient(mockHttpClient);
    }

    @Test
    void hackerNewsTopStoriesReturnsNonEmptyArray() throws Exception {
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn("1,2,3");

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockResponse);

        long[] result = Task5.hackerNewsTopStories();
        assertArrayEquals(new long[] {1, 2, 3}, result);
    }

    @Test
    void hackerNewsTopStoriesReturnsEmptyArray() throws Exception {
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenThrow(new RuntimeException("Mocked exception"));

        long[] result = Task5.hackerNewsTopStories();
        assertArrayEquals(new long[0], result);
    }

    @Test
    void news_ReturnsTitle() throws Exception {
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn("{\"title\":\"Test Title\"}");

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenReturn(mockResponse);

        String result = Task5.news(1);
        assertEquals("Test Title", result);
    }

    @Test
    void news_ReturnsUnknownTitleOnException() throws Exception {
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenThrow(new RuntimeException("Mocked exception"));

        String result = Task5.news(0);
        assertEquals("Unknown News", result);
    }
}
