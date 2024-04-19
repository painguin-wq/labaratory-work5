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
            for (Furnish furnishValue : Furnish.values()) {
                if (furnish.equals(furnishValue.name()) || furnish.toUpperCase().equals(furnishValue.name())) {
                    furnishNumber = furnishValue.ordinal();
                }
            }
            if (furnishNumber == -1){
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
        return "Count elements of furnish greater than entered value";
    }

    @Override
    public String getMask() {
        return "count_greater_than_furnish <NONE, BAD, LITTLE>";
    }
}
