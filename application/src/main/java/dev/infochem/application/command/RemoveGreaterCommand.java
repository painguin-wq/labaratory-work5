package dev.infochem.application.command;

import dev.infochem.application.model.Flat;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

public class RemoveGreaterCommand extends DefaultCommand {
    @CommandAction
    void remove() {
        System.out.println("Введите название элемента, который вы хотите выбрать.\n");
        String listOfElements = """
                    Список доступных элементов:\s
                    \t- name (String)
                    \t- coordinates (Coordinates)
                    \t- area (Float)
                    \t- numberOfRooms (Long)
                    \t- numberOfBathrooms (Long)
                    \t- timeToMetroByTransport (Float)
                    \t- furnish (Furnish)
                    \t- house (House)
                    """;
        System.out.print(listOfElements);
        CommandsScanner scanner = new CommandsScanner(System.in);
    }
    @Override
    public String getCaption() {
        return "Output the greater element of the collection and delete it";
    }

    @Override
    public String getMask() {
        return "remove_greater";
    }
}
