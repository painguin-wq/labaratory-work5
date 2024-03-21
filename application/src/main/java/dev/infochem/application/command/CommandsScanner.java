package dev.infochem.application.command;

import java.io.InputStream;
import java.util.Scanner;

public class CommandsScanner {
    private final Scanner scanner;
    private final String EXIT_STRING = "exit";
    public CommandsScanner(InputStream source) {
        scanner = new Scanner(source);
    }

    private <T> T checkExit(T command) {
        if (command.toString().equals(EXIT_STRING)) {
            System.exit(0);
        }
        return command;
    }

    public String nextCommand() {
        return checkExit(scanner.next());
    }

    public Integer nextInt() {
        return checkExit(scanner.nextInt());
    }

    public Double nextDouble() {
        return checkExit(scanner.nextDouble());
    }

    public Float nextFloat() {
        return checkExit(scanner.nextFloat());
    }

    public Long nextLong() {
        return checkExit(scanner.nextLong());
    }

}
