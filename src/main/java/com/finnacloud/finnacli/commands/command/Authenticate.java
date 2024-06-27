package com.finnacloud.finnacli.commands.command;

import com.finnacloud.finnacli.Runtime;
import com.finnacloud.finnacli.commands.CommandInterface;
import com.finnacloud.finnacli.commands.CommandStruct;

public class Authenticate extends CommandStruct implements CommandInterface {
    public Authenticate() {
        super("login", "Authenticate with the FinnaCloud API.", "login <{API_KEY}>");
    }

    @Override
    public void run(String[] args) {
        if (args.length == 1) {
            System.out.println("Please provide an API key.");
            return;
        }
        // args cannot be >= 2
        if (args.length > 2) {
            System.out.println("Too many arguments provided. Please provide only an API key.");
            return;
        }


    }
}
