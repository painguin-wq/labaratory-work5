package dev.infochem.application.command;

import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

/**
 * The type Help command.
 */
public class HelpCommand extends DefaultCommand {
    /**
     * Gets help.
     */
    @CommandAction
    void getHelp() {
        getProject().getCommands().forEach(command ->
                System.out.printf("%n%s - Маска для использования команды: %s%nОписание команды: %s%n", getProject().getCommands().getNameByType(command.getClass()), command.getMask(), command.getCaption()));
        System.out.println();
    }
    @Override
    public String getCaption() {
        return "Display help for available commands";
    }

    @Override
    public String getMask() {
        return "help";
    }
}
