package dev.infochem.application.command;

import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

public class RemoveGreaterCommand extends DefaultCommand {
    @CommandAction
    void remove() {

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
