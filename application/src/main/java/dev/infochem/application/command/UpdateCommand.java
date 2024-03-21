package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;
import java.util.List;

public class UpdateCommand extends DefaultCommand {
    @CommandAction
    void update(int id) {
        FlatManager dataManager = FileManagerFactory.create();
        ArrayDeque<Flat> flatArrayDeque = dataManager.getData();
        List<Flat> flats = flatArrayDeque.stream().filter(flat -> flat.getId() == id).toList();
        if (flats.isEmpty()) {
            throw new UnsupportedOperationException("Cannot find flat with id = %d".formatted(id));
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
