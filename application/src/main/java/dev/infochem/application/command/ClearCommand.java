package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.clilibrary.DefaultCommand;

/**
 * The type Clear command.
 */
public class ClearCommand extends DefaultCommand {

    /**
     * Clear.
     */
    void clear() {
        FlatManager dataManager = FileManagerFactory.create();
        dataManager.getData().clear();
    }
    @Override
    public String getCaption() {
        return "Clean up the collection";
    }

    @Override
    public String getMask() {
        return "clear";
    }
}
