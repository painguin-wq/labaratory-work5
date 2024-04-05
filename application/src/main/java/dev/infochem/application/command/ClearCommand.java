package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.clilibrary.DefaultCommand;

public class ClearCommand extends DefaultCommand {

    void clear() {
        FlatManager dataManager = FileManagerFactory.create();
        dataManager.getData().clear();
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
