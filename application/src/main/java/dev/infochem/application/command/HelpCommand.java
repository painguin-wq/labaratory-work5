package dev.infochem.application.command;

import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

public class HelpCommand extends DefaultCommand {
    @CommandAction
    void getHelp() {
        getProject().getCommands().forEach(command ->
                System.out.printf("%s - %s%n%s%n", getProject().getCommands().getNameByType(command.getClass()), command.getMask(), command.getCaption()));
    }
    @Override
    public String getCaption() {
        return null;
    }

    @Override
    public String getMask() {
        return null;
    }
}
