package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class HackerNews {

    private HackerNews() {

    }

    public static long[] hackerNewsTopStories() {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json")).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String responseBody = httpResponse.body();

            return Arrays.stream(responseBody
                    .replaceAll("[\\[\\]]", "")
                    .split(","))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }

    }

    public static String newsTitle(long id) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String responseBody = httpResponse.body();

            Pattern pattern = Pattern.compile("\"title\":\"([^\"]*)\"");
            Matcher matcher = pattern.matcher(responseBody);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return null;
            }

        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

}
