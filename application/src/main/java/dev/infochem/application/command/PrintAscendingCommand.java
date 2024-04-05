package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

/**
 * The type Print ascending command.
 */
public class PrintAscendingCommand extends DefaultCommand {
    /**
     * Print.
     */
    @CommandAction
    void print() {
        FlatManager fileManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = fileManager.getData();
        for (Flat flat : flats) {
            System.out.println(flat);
        }
    }
    @Override
    public String getCaption() {
        return "output the elements of the collection in ascending order";
    }

    @Override
    public String getMask() {
        return "print_ascending";
    }
}
