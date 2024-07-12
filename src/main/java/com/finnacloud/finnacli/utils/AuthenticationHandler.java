package com.finnacloud.finnacli.utils;

import java.io.*;

public class AuthenticationHandler {
    public static String getToken() throws Exception {
         File file = new File(System.getProperty("user.home") + "/.finnacloud/.token");
         if (!file.exists()) {
            return null;
         }
        // get text from file
        return (new BufferedReader(new FileReader(file))).readLine();
    }
}
