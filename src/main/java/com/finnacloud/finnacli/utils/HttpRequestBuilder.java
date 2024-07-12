package com.finnacloud.finnacli.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestBuilder {
    public static String get(String url, String method, String body) {
        return get(url, method, body, null);
    }
    public static String get(String url, String method, String body, String token) {
        String response = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("User-Agent", "FinnaCloud CLI/1.0 (Java %s on %s at %d)".formatted(System.getProperty("java.version"), System.getProperty("os.name"), System.currentTimeMillis() / 1000));
            con.setRequestProperty("Content-Type", "application/json");
            // if token is not null, add it to the request
            if (token != null) {
                con.setRequestProperty("Authorization", "Bearer " + token);
            }
            con.setDoOutput(true);
            if (body != null) {
                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.writeBytes(body);
                    wr.flush();
                }
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuilder responseBuilder = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        responseBuilder.append(inputLine);
                    }
                    response = responseBuilder.toString();
                }
            } else {
                return "An error occurred while making the request.\n  - Status code: " + responseCode + "\n  - Response message: " + con.getResponseMessage() + "\n  - Host: " + con.getURL().getHost();
            }
        } catch (Exception e) {
            System.out.printf("An error occurred while making the request: %s%n", e.getMessage());
        }
        return response;
    }
}
