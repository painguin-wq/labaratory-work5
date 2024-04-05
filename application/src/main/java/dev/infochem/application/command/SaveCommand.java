package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

/**
 * The type Save command.
 */
public class SaveCommand extends DefaultCommand {
    /**
     * Save.
     */
    @CommandAction
    void save() {
        FlatManager dataManager = FileManagerFactory.create();
        dataManager.saveData(dataManager.getData());
    }

    @Override
    public String getCaption() {
        return "Save the collection to a file";
    }

    @Override
    public String getMask() {
        return "save";
    }
}
