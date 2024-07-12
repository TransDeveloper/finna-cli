package com.finnacloud.finnacli.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// i have been cursed with homosexuality
public class YAMLConfiguration {
    private File yamlFile;
    private Yaml yaml;
    private Map<String, Object> data;

    public YAMLConfiguration(File yamlFile) throws IOException {
        this.yamlFile = yamlFile;
        this.yaml = new Yaml();
        try (FileInputStream inputStream = new FileInputStream(yamlFile)) {
            this.data = yaml.load(inputStream);
            if (this.data == null) {
                this.data = new HashMap<>(); // Initialize data to an empty map if it's null
            }
        } catch (IOException e) {
            throw new IOException("Failed to load YAML configuration: " + e.getMessage(), e);
        }
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public void set(String key, Object value) {
        this.data.put(key, value);
    }

    public Boolean create(){
        try {
            this.yamlFile.getParentFile().mkdirs();
            this.yamlFile.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public void save() throws IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        try (FileWriter writer = new FileWriter(this.yamlFile)) {
            yaml.dump(this.data, writer);
        } catch (IOException e) {
            throw new IOException("Failed to save YAML configuration: " + e.getMessage(), e);
        }
    }

    public void delete(String key) {
        this.data.remove(key);
    }
}