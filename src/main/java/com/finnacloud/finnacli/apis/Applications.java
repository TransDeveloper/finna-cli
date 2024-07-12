package com.finnacloud.finnacli.apis;

import com.finnacloud.finnacli.apis.api.Resources;

import java.util.HashMap;

public class Applications {
    private HashMap<String, Class> apis = new HashMap<>();

    public Applications() {
        apis.put("resources", Resources.class);
    }

    public HashMap<String, Class> getApis() {
        return apis;
    }
}
