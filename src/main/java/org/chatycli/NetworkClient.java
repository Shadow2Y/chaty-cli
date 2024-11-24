package org.chatycli;

import org.chatycli.Interface.Config;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NetworkClient {
    private static final String serverAddress = Config.getServerHost();
    private static final int serverPort  = Config.getServerPort();
    private static List<Cookie> cookies;

    public static void loginUser(String username, String password) {
        try {
            System.out.println(" :: Initiating client login :: ");
            final String loginUrl = serverAddress + "/login";
            String payload = "username=" + username + "&password=" + password;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(loginUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            // Send the request and get the response
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response status and body
            System.out.println("Response Code :: " + response.statusCode());
            System.out.println("Response Body :: " + response.body());

            // Get and print cookies from the response headers
            HttpHeaders headers = response.headers();
            List<String> cookies = headers.allValues("Set-Cookie");
            if (!cookies.isEmpty()) {
                for (String cookie : cookies) {
                    System.out.println("Cookie :: " + cookie);
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
    }
    private static class Cookie {
        private String id;
        private Date setDate;
        private Date expDate;
    }
}