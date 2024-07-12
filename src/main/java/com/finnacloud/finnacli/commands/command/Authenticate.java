package com.finnacloud.finnacli.commands.command;

import com.finnacloud.finnacli.Constants;
import com.finnacloud.finnacli.Runtime;
import com.finnacloud.finnacli.commands.CommandInterface;
import com.finnacloud.finnacli.commands.CommandStruct;
import com.finnacloud.finnacli.utils.AuthenticationHandler;
import com.finnacloud.finnacli.utils.HttpsRequestBuilder;

import java.io.*;

public class Authenticate extends CommandStruct implements CommandInterface {
    public Authenticate() {
        super("login", "Authenticate with the FinnaCloud API.", "login {API_KEY}");
    }

    @Override
    public void run(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Invalid number of arguments provided. Use 'help login' to list all commands.");
            return;
        }

        switch (args[1]) {
            case "internal":
                internalAuthentication();
                break;
            case "token":
                System.out.println("Continuing with Token Authentication...");
                // get all input after args[1]
                StringBuilder token = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    token.append(args[i]).append(" ");
                }

                if (token.toString().trim().isEmpty()) {
                    System.out.println("No token provided.");
                    return;
                }

                try {
                    if (AuthenticationHandler.saveToken(token.toString().trim()) && !Runtime.isSilent) {
                        System.out.println("Token saved successfully.");
                        System.out.println("Authentication successful.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Unsupported authentication method.");
                break;
        }
    }

    private void internalAuthentication() throws IOException {
        if (!Runtime.isSilent) {
            System.out.println("Continuing with Internal Authentication...");
        }
        System.out.printf("Please visit %s to generate a one-time authentication code.%n", Constants.BASE_AUTH_URL + "otp");
        String otp = readOTPFromConsole();
        if (otp == null || otp.length() > 12 || otp.length() < 8) {
            System.out.println("The code provided does not match the expected format.");
            return;
        }

        String responseBody = HttpsRequestBuilder.get(Constants.BASE_AUTH_URL + "otp?otp=" + otp, "POST", null);
        processAuthenticationResponse(responseBody);
    }

    private String readOTPFromConsole() {
        System.out.print("Enter the one-time authentication code: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine().replaceAll("[^0-9]", "").trim();
        } catch (Exception e) {
            System.out.printf("An error occurred while reading the one-time authentication code: %s%n", e.getMessage());
            return null;
        }
    }

    private void processAuthenticationResponse(String responseBody) {
        try {
            String token = responseBody.split("\"")[3]; // Assuming the response format is consistent
            if (AuthenticationHandler.saveToken(token) && !Runtime.isSilent) {
                System.out.println("Token saved successfully.");
                System.out.println("Authentication successful.");
            }
        } catch (Exception e) {
            System.out.printf("An error occurred while processing the authentication response: %s%n", e.getMessage());
        }
    }
}