package dev.infochem.application.command;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The type Commands scanner.
 */
public class CommandsScanner {
    private final Scanner scanner;
    private final String EXIT_STRING = "exit";

    /**
     * Instantiates a new Commands scanner.
     *
     * @param source the source
     */
    public CommandsScanner(InputStream source) {
        scanner = new Scanner(source);
    }

    private <T> T checkExit(T command) {
        if (command.toString().equals(EXIT_STRING)) {
            System.exit(0);
        }
        return command;
    }

    /**
     * Next command string.
     *
     * @return the string
     */
    public String nextCommand() {
        return checkExit(scanner.next());
    }

    /**
     * Next int integer.
     *
     * @return the integer
     */
    public Integer nextInt() {
        return checkExit(scanner.nextInt());
    }

    /**
     * Next double double.
     *
     * @return the double
     */
    public Double nextDouble() {
        return checkExit(scanner.nextDouble());
    }

    /**
     * Next float float.
     *
     * @return the float
     */
    public Float nextFloat() {
        return checkExit(scanner.nextFloat());
    }

    /**
     * Next long long.
     *
     * @return the long
     */
    public Long nextLong() {
        return checkExit(scanner.nextLong());
    }

}
