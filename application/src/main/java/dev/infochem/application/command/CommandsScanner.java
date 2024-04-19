package dev.infochem.application.command;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Function;

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

    private <T> T checkedCast(Function<String, T> castFunc, String value) {
        try {
            return castFunc.apply(value);
        } catch (Exception ex) {
            throw new ClassCastException("Failed cast %s to the givven type".formatted(value));
        }
    }

    public String nextCommand() {
        return checkExit(scanner.nextLine());
    }

    public Integer nextInt() {
        return checkedCast(Integer::parseInt, checkExit(scanner.nextLine()));
    }

    public Double nextDouble() {
        return checkedCast(Double::parseDouble,checkExit(scanner.nextLine()));
    }

    public Float nextFloat() {
        return checkedCast(Float::parseFloat, checkExit(scanner.nextLine()));
    }

    public Long nextLong() {
        return checkedCast(Long::parseLong, checkExit(scanner.nextLine()));
    }

}
