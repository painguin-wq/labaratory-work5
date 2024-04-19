package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

/**
 * The type Remove first command.
 */
public class RemoveFirstCommand extends DefaultCommand {
    /**
     * Remove.
     */
    @CommandAction
    void remove() {
        FlatManager flatManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = flatManager.getData();
        if (!flats.isEmpty()) {
            flats.removeFirst();
            flatManager.resolveIds();
        } else {
            System.err.println("There are no items to delete in the database");
        }
    }

    @Override
    public String getCaption() {
        return "Delete the first item in the collection";
    }

    @Override
    public String getMask() {
        return "remove_first";
    }
}
