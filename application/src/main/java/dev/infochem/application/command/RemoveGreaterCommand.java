package dev.infochem.application.command;

import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

public class RemoveGreaterCommand extends DefaultCommand {
    @CommandAction
    void remove() {

    }
    @Override
    public String getCaption() {
        return "Output the greater element of the collection and delete it";
    }

    @Override
    public String getMask() {
        return "remove_greater";
    }
}
