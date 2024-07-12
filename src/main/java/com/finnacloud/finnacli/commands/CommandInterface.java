package com.finnacloud.finnacli.commands;

import java.io.IOException;

public interface CommandInterface {
    void run(String[] args) throws IOException;
}
