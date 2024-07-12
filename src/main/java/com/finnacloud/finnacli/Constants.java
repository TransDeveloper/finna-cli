package com.finnacloud.finnacli;

import com.finnacloud.finnacli.utils.AuthenticationHandler;

import java.io.File;

public class Constants {
    public static final Boolean PRODUCTION = false;
    public static final String API_URL = PRODUCTION ? "https://engelberg-stg1.corp.finnacloud.com/api" : "http://localhost:8080/api";
    public static final String BASE_AUTH_URL = "https://connect.finnacloud.com/";
    public static final File BASE_CONFIG_FILE = new File(System.getProperty("user.home") + "/.finnacloud/settings.yaml");
    public static final AuthenticationHandler authenticationHandler = AuthenticationHandler.getInstance();
}