package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

/**
 * The type Show command.
 */
public final class ShowCommand extends DefaultCommand {
    /**
     * Gets info.
     */
    @CommandAction
    public void getInfo() {
        FlatManager fileManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = fileManager.getData();
        for (Flat flat : fileManager.sortFlats(flats)) {
            System.out.println(flat);
        }
    }
    @Override
    public String getCaption() {
        return "Output all elements of the collection in string representation to the standard output stream";
    }


    @Override
    public String getMask() {
        return "show";
    }
}
