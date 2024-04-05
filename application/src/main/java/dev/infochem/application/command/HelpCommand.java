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
                System.out.printf("%s - %s%n%s%n", getProject().getCommands().getNameByType(command.getClass()), command.getMask(), command.getCaption()));
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
