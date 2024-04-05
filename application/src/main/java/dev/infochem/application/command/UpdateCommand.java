package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;
import java.util.List;

/**
 * The type Update command.
 */
public class UpdateCommand extends DefaultCommand {
    /**
     * Update.
     *
     * @param id the id
     */
    @CommandAction
    void update(Integer id) {
        FlatManager dataManager = FileManagerFactory.create();
        ArrayDeque<Flat> flatArrayDeque = dataManager.getData();
        List<Flat> flats = flatArrayDeque.stream().filter(flat -> flat.getId() == id).toList();
        if (flats.isEmpty()) {
            throw new UnsupportedOperationException("Cannot find flat with id = %d".formatted(id));
        }

    }
    @Override
    public String getCaption() {
        return "Update the value of the collection item whose id is equal to the given one";
    }

    @Override
    public String getMask() {
        return "update <id>";
    }
}
