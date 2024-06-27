package com.finnacloud.finnacli.commands;

public class CommandStruct {
    private String command;
    private String description;
    private String usage;

    public CommandStruct(String command, String description, String usage) {
        this.command = command.toLowerCase();
        this.description = description;
        this.usage = usage;
    }
    public void run(String[] args) {
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
