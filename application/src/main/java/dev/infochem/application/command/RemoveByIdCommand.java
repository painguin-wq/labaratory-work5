package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;
import java.util.Optional;

public class RemoveByIdCommand extends DefaultCommand {
    @CommandAction
    void remove(Integer id) {
        FlatManager flatManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = flatManager.getData();
        if (!flats.isEmpty()) {
            Optional<Flat> flatOpt = flats.stream().filter(flatFromStream -> flatFromStream.getId() == id).findFirst();
            if (flatOpt.isPresent()) {
                flats.remove(flatOpt.get());
            } else {
                System.err.printf("There are no item with id = %d%n", id);
            }
            flatManager.saveData(flats);
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
