package com.finnacloud.finnacli.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// i want to end it all
public class ConfigurationManager {
    private YAMLConfiguration configuration;
    private File configFile;


    public ConfigurationManager(File configFile) {
        this.configFile = configFile;
    }

    public YAMLConfiguration getSettings() throws IOException {
        if (!configFile.exists()){
            configFile.createNewFile();
            throw new IOException("Configuration file not found. Created a new one.");
        }
        configuration = new YAMLConfiguration(configFile);
//        Map<String, String> map = new HashMap<>();
//        map.put("say", "gex");
//        configuration.set("youshould", map);
//        configuration.set("ifuckinghatelife", "sobadly");
//
//        Map<String, String> x = (Map<String, String>) configuration.get("hello");
//
//        System.out.println(
//                x.get("ifuckinghatelife")
//        );
//        configuration.save();
        return configuration;
    }
}
