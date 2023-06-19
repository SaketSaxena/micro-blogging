package com.microblogging.microblogging.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TwitterStreamService {

    public TwitterStreamService(@Value("${twitter.auth.token}") String twitterAuthToken) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.twitter.com/2/tweets/sample/stream"))
                .header("Authorization", "Bearer "+ twitterAuthToken)
                .build();
    }

    private void startStream(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        HttpHeaders headers = response.headers();
        String responseBody = response.body();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Headers: " + headers);
        System.out.println("Response Body: " + responseBody);

        //Looks like Twitter v2 api is not free to sample stream the tweets,
        // if that is possible then we can stream the tweet from here and
        // send it to websocket and store it in the database.

    }
}
