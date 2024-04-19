package dev.infochem.application.command;

import dev.infochem.clilibrary.Application;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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
            System.out.printf("Данный файл (path: %s) не существует или не был найден%n", path);
            return;
        }
        if (!file.canRead()) {
            System.out.printf("Данный файл(Путь: %s) не может быть прочитан %n", path);
            return;
        }
        ArrayList<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().filter(Objects::nonNull).filter((line) -> !line.isEmpty()).forEach(commands::add);
            for (String cmd : commands) {
                if (cmd.contains(getProject().getCommands().getNameByType(ExecuteScriptCommand.class))) {
                    System.out.printf("Файл скрипта не может рекурсивно содержать комманду %s%n", getProject().getCommands().getNameByType(ExecuteScriptCommand.class));
                    return;
                }
            }
        } catch (IOException e) {
            System.out.printf("Ошибка чтения файла (Путь: %s)%n", path);
            return;
        }
        for (String cmd : commands) {
            Application.executeActions(getProject(), cmd.split(" "));
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
