package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;
import java.util.Optional;

/**
 * The type Remove by id command.
 */
public class RemoveByIdCommand extends DefaultCommand {
    /**
     * Remove.
     *
     * @param id the id
     */
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
        } else {
            System.err.println("There are no items to delete in the database");
        }
    }

    @Override
    public String getCaption() {
        return "Remove an item from the collection by its id";
    }

    @Override
    public String getMask() {
        return "remove_by_id <id>";
    }
}
