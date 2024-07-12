package com.finnacloud.finnacli.utils;

import com.finnacloud.finnacli.Constants;
import java.io.IOException;

public class AuthenticationHandler {
    private static YAMLConfiguration cfm = null;
    private static AuthenticationHandler instance = null;
    private static String cachedToken = null;

    private AuthenticationHandler() {
        try {
            ConfigurationManager configurationManager = new ConfigurationManager(Constants.BASE_CONFIG_FILE);
            cfm = configurationManager.getSettings();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings", e);
        }
    }

    public static synchronized AuthenticationHandler getInstance() {
        if (instance == null) {
            instance = new AuthenticationHandler();
        }
        return instance;
    }

    public String getToken() throws Exception {
        if (cachedToken == null) {
            cachedToken = (String) cfm.get("token");
        }
        return cachedToken;
    }

    public void saveToken(String token) throws Exception {
        cfm.set("token", token);
        cfm.save();
        cachedToken = token; // Update the cached token
    }

    public void deleteToken() throws Exception {
        cfm.delete("token");
        cachedToken = null; // Clear the cached token
    }
}