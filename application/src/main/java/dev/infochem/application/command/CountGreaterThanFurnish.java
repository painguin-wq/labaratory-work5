package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Flat;
import dev.infochem.application.model.Furnish;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

public class CountGreaterThanFurnish extends DefaultCommand {
    @CommandAction
    void count(String furnish) {
        int furnishNumber = -1;
        while (furnishNumber == -1) {
            try {
                furnishNumber = Furnish.valueOf(furnish).ordinal();
            } catch (IllegalArgumentException ex) {
                System.out.println("В параметр команды count_greater_than_furnish нужно передать один из элементов из enum Furnish.\nДоступные элементы:  NONE, BAD, LITTLE");
            }
        }
        FlatManager fileManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = fileManager.getData();
        int counter = 0;
        for (Flat flat : fileManager.sortFlats(flats)) {
            if (flat.getFurnish().ordinal() > furnishNumber) {
                counter++;
            }
        }
        System.out.printf("Количество элементов Furnish больше заданого равно - %d%n", counter);
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
