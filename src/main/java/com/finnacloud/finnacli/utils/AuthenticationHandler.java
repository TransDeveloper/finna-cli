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

    public static Boolean saveToken(String token) throws Exception {
        File file = new File(System.getProperty("user.home") + "/.finnacloud/.token");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        // write token to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(token);
        writer.close();
        return true;
    }

    public static void deleteToken() throws Exception {
        File file = new File(System.getProperty("user.home") + "/.finnacloud/.token");
        if (file.exists()) {
            file.delete();
        }
    }
}
