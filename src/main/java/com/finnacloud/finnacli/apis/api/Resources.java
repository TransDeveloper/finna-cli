package com.finnacloud.finnacli.apis.api;

import com.finnacloud.finnacli.Constants;
import com.finnacloud.finnacli.apis.ApplicationStruct;
import com.finnacloud.finnacli.utils.AuthenticationHandler;
import com.finnacloud.finnacli.utils.HttpsRequestBuilder;

import java.util.HashMap;

public class Resources implements ApplicationStruct {
    public Resources() {}

    public void getResources(Integer limit, Integer offset, HashMap<String, String> filters) throws Exception {
        String x = HttpsRequestBuilder.get(Constants.API_URL + "/resources", "GET", null, AuthenticationHandler.getToken());
        System.out.println(x);
    }
}
