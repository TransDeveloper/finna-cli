package com.finnacloud.finnacli;

import com.finnacloud.finnacli.commands.CommandStruct;
import com.finnacloud.finnacli.commands.command.Authenticate;
import com.finnacloud.finnacli.commands.command.Get;
import com.finnacloud.finnacli.commands.command.Help;
import java.util.ArrayList;

public class Runtime {
    private static Runtime runtime;
    public static ArrayList<CommandStruct> commands = new ArrayList<>();
    protected static final String version = "1.0";

    public static Boolean isSilent = false;

    public Runtime(String[] arguments) {
        commands.add(new Help());
        commands.add(new Authenticate());
        commands.add(new Get());

        String args = String.join(" ", arguments);
        if (args.contains("--silent")) {
            isSilent = true;
            return;
        }
        System.out.printf("FinnaCloud CLI v%s\nCopyright (c) 2024 TheFinnaCompany, Ltd. All rights reserved.\n\n", version);
    }

    public static void main(String[] args) throws Exception {
        new Runtime(args);

        if (args.length == 0) {
            System.out.println("No command provided. Use 'help [command]' to get help for a specific command.");
            return;
        }

        int i = 0;
        for (CommandStruct commandStruct : Runtime.commands) {
            i++;
            if (commandStruct.getCommand().equals(args[0])) {
                String[] arg = String.join(" ", args).replace("--silent", "").split(" ");
                ArrayList<String> argList = new ArrayList<>();
                for (String s : arg) {
                    if (!s.isEmpty()) {
                        argList.add(s);
                    }
                }
                arg = argList.toArray(new String[0]);
                commandStruct.run(arg);
                break;
            }
            if (i == Runtime.commands.size()) {
                System.out.println("The command '" + args[0] + "' was not found. Use 'help [command]' to get help for a specific command.");
            }
        }
    }
}
