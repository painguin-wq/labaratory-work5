package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

public class RemoveHeadCommand extends DefaultCommand {
    @CommandAction
    void remove() {
        FlatManager flatManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = flatManager.getData();
        if (!flats.isEmpty()) {
            Flat deletedFlat = flats.removeFirst();
            flatManager.saveData(flats);
            System.out.printf("Flat deleted: %s%n", deletedFlat);
        } else {
            System.err.println("There are no items to delete in the database");
        }

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
