package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

public class SaveCommand extends DefaultCommand {
    @CommandAction
    void save() {
        FlatManager dataManager = FileManagerFactory.create();
        dataManager.saveData(dataManager.getData());
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
