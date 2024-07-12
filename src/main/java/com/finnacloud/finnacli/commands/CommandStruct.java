package com.finnacloud.finnacli.commands;

import java.io.IOException;

public class CommandStruct {
    private final String command;
    private final String description;
    private final String usage;

    public CommandStruct(String command, String description, String usage) {
        this.command = command.toLowerCase();
        this.description = description;
        this.usage = usage;
    }
    public void run(String[] args) throws IOException {
        System.out.println("Command not implemented");
    }

    public String getCommand() {
        return command;
    }
    public String getDescription() {
        return description;
    }
    public String getUsage() {
        return usage;
    }
}
