package com.finnacloud.finnacli.commands.command;

import com.finnacloud.finnacli.Runtime;
import com.finnacloud.finnacli.apis.Applications;
import com.finnacloud.finnacli.apis.api.Resources;
import com.finnacloud.finnacli.commands.CommandInterface;
import com.finnacloud.finnacli.commands.CommandStruct;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Get extends CommandStruct implements CommandInterface {
    public static Applications applications = new Applications();

    public Get() {
        super("Get", "Get data from the FinnaCloud API.", "get " + applications.getApis().keySet());
        apis.put("resources", "getResources");
    }

    // array of hashmap to void
    private HashMap<String, String> apis = new HashMap<>(); // string, methodName

    @Override
    public void run(String[] args) {
        if (args.length == 1) {
            System.out.println("Please provide a resource type. Available resource types are: " + apis.keySet());
            return;
        }
        if(!apis.containsKey(args[1].toLowerCase())) {
            System.out.println("Invalid resource type. Available resource types are: " + apis.keySet());
            return;
        }

        apis.forEach((key, value) -> {
            if (args[1].equalsIgnoreCase(key)) {
                try {
                    this.getClass().getMethod(value).invoke(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getResources() { // we will do some fuckery
        if (!Runtime.isSilent) {
            System.out.println("Getting resources...");
        }
        try {
            Resources resources = (Resources) applications.getApis().get("resources").getDeclaredConstructor().newInstance();
            resources.getResources(5, 0, null);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
