package com.finnacloud.finnacli.commands.command;

import com.finnacloud.finnacli.Runtime;
import com.finnacloud.finnacli.commands.CommandStruct;
import com.finnacloud.finnacli.commands.CommandInterface;

public class Help extends CommandStruct implements CommandInterface {
    public Help() {
        super("help", "Lists all commands or provides help for a specific command.", "help [command]");
    }

    @Override
    public void run(String[] args) {
        if (args.length == 1) {
            System.out.println("Listing all commands:");
            for (CommandStruct commandStruct : Runtime.commands) {
                System.out.println(">" + commandStruct.getUsage() + " - " + commandStruct.getDescription());
            }
            System.out.println("{x} = required, [x] = optional, <x> = flag");
        } else {
            int i = 0;
            for (CommandStruct commandStruct : Runtime.commands) {
                i++;
                if (commandStruct.getCommand().equals(args[1])) {
                    System.out.printf("Listing command '%s':\nUsage: %s\nDescription: %s\n",
                            commandStruct.getCommand(), commandStruct.getUsage(), commandStruct.getDescription());
                    break;
                }
                if (i == Runtime.commands.size()) {
                    System.out.println("Help> The command '" + args[1] + "' was not found.");
                }
            }
        }
    }
}
