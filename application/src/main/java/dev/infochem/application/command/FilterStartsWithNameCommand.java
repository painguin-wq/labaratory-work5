package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

/**
 * The type Filter starts with name command.
 */
public class FilterStartsWithNameCommand extends DefaultCommand {
    /**
     * Filter.
     *
     * @param prefix the prefix
     */
    @CommandAction
    void filter(String prefix) {
        FlatManager fileManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = fileManager.getData();
        for (Flat flat : flats) {
            if (flat.getName().startsWith(prefix)){
                System.out.println(flat);
            }
        }
    }
    @Override
    public String getCaption() {
        return "output items whose name field value starts with the specified substring";
    }

    @Override
    public String getMask() {
        return "filter_starts_with_name <prefix>";
    }
}
