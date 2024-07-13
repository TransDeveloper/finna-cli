package com.finnacloud.finnacli.apis.api;

import com.finnacloud.finnacli.Constants;
import com.finnacloud.finnacli.apis.ApplicationStruct;
import com.finnacloud.finnacli.utils.AuthenticationHandler;
import com.finnacloud.finnacli.utils.CommandLineTable;
import com.finnacloud.finnacli.utils.HttpRequestBuilder;
import com.finnacloud.finnacli.utils.HttpsRequestBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resources implements ApplicationStruct {
    public Resources() {}

    public void getResources(Integer limit, Integer offset, HashMap<String, String> filters, Boolean fullNotes) throws Exception {

        CommandLineTable st = new CommandLineTable();


        st.setShowVerticalLines(true);


        String resourcesJson = HttpRequestBuilder.get(Constants.API_URL + "/resources", "GET", null, Constants.authenticationHandler.getToken());
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> resources = gson.fromJson(resourcesJson, type);

        int index = 0;
        if ((Boolean) resources.get("success")) {
            st.setHeaders("", "Resource Name", "Status", "Notes", "IPv4 Addresses", "IPv6 Addresses", "Created On");
            Map<String, Object> resourcesList = (Map<String, Object>) resources.get("resources");
            ArrayList sharedResourcesList = (ArrayList) resourcesList.get("shared");

            for (Object item : sharedResourcesList) {
                Map<String, Object> sharedResource = (Map<String, Object>) item;
                List<String> notesList = (List<String>) sharedResource.get("notes");
                String notes = String.join(" ", notesList);
                if (notes.length() > 16 && !fullNotes) {
                    notes = notes.substring(0, 16) + "...";
                }
                st.addRow(""+index, sharedResource.get("resourceName").toString(), sharedResource.get("status").toString(), notes, sharedResource.get("ipv4Addresses").toString(), sharedResource.get("ipv6Addresses").toString(), sharedResource.get("createdOn").toString());
                index++;
            }

            ArrayList ownedResourcesList = (ArrayList) resourcesList.get("owned");
            for (Object item : ownedResourcesList) {
                Map<String, Object> ownedResource = (Map<String, Object>) item;
                List<String> notesList = (List<String>) ownedResource.get("notes");
                String notes = String.join(" ", notesList);
                if (notes.length() > 16 && !fullNotes) {
                    notes = notes.substring(0, 16) + "...";
                }
                st.addRow(""+index, ownedResource.get("resourceName").toString(), ownedResource.get("status").toString(), notes, ownedResource.get("ipv4Addresses").toString().replace("[", "").replace("]", ""), ownedResource.get("ipv6Addresses").toString().replace("[", "").replace("]", ""), ownedResource.get("createdOn").toString());
                index++;
            }

            st.print();
        } else {
            System.out.println("An error occurred while fetching resources: " + resources.get("error"));
        }
    }
}