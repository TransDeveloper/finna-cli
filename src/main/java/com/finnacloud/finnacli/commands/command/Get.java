package com.finnacloud.finnacli.commands.command;

import com.finnacloud.finnacli.Runtime;
import com.finnacloud.finnacli.apis.Applications;
import com.finnacloud.finnacli.apis.api.Resources;
import com.finnacloud.finnacli.commands.CommandInterface;
import com.finnacloud.finnacli.commands.CommandStruct;

import java.util.HashMap;

public class Get extends CommandStruct implements CommandInterface {
    private static final Applications applications = new Applications();
    private final HashMap<String, String> apis = new HashMap<>();
    private String[] arguments = new String[0];

    public Get() {
        super("Get", "Get data from the FinnaCloud API.", "get " + applications.getApis().keySet());
        apis.put("resources", "getResources");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide a resource type. Available resource types are: " + apis.keySet());
            return;
        }

        String resourceType = args[1].toLowerCase();
        if (!apis.containsKey(resourceType)) {
            System.out.println("Invalid resource type. Available resource types are: " + apis.keySet());
            return;
        }

        String methodName = apis.get(resourceType);

        if (args.length > 2) {
            arguments = new String[args.length - 2];
            System.arraycopy(args, 2, arguments, 0, args.length - 2);
        }

        try {
            this.getClass().getMethod(methodName).invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getResources() {
        if (!Runtime.isSilent) {
            System.out.println("Getting resources...");
        }

        try {
            StringBuilder argBuilder = new StringBuilder();
            for (String arg : arguments) {
                argBuilder.append(arg).append(" ");
            }
            System.out.print(!argBuilder.toString().replaceAll(" ", "").isEmpty() ? argBuilder.toString() + "\n" : "");
            // TODO: Remove the line above. (i'll probably forget)

            Resources resources = (Resources) applications.getApis().get("resources").getDeclaredConstructor().newInstance();
            resources.getResources(null, null, null, true);
        } catch (Exception e) {
            System.out.printf("An error occurred while making the request: %s%n", e.getMessage());
        }
    }
}
