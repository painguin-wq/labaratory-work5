package dev.infochem.application.command;

import dev.infochem.clilibrary.Action;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Execute script command.
 */
public class ExecuteScriptCommand extends DefaultCommand {
    /**
     * Execute.
     *
     * @param path the path
     */
    @CommandAction
    void execute(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.err.printf("There is no such file (path: %s)%n", path);
            return;
        }
        if (file.canRead()) {
            System.err.printf("This file(path: %s) cannot be read%n", path);
            return;
        }
        ArrayList<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().map((line) -> commands.addAll(List.of(line.split(" ")))).close();
        } catch (IOException e) {
            System.err.printf("File reading error (path: %s)%n", path);
            return;
        }
        Action[] actions = getProject().getParser().parse(commands.toArray(new String[0]));
        for (Action action : actions) {
            action.execute();
        }
    }

    @Override
    public String getCaption() {
        return "Read and execute a script from a specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.";
    }

    @Override
    public String getMask() {
        return "execute_script <path>";
    }
}
