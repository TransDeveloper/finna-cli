package com.finnacloud.finnacli.apis.api;

import com.finnacloud.finnacli.apis.ApplicationStruct;
import com.finnacloud.finnacli.utils.HttpsRequestBuilder;

import java.util.HashMap;

public class Resources implements ApplicationStruct {
    public Resources() {}

    public void getResources(Integer limit, Integer offset, HashMap<String, String> filters) {
        String x = HttpsRequestBuilder.get("https://engelberg-stg1.corp.finnacloud.com/api/v1/resources", "GET", null);
        System.out.println(x);
    }
}
