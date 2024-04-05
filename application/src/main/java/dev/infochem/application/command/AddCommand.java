package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.*;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

/**
 * The type Add command.
 */
public class AddCommand extends DefaultCommand {
    private final CommandsScanner scanner = new CommandsScanner(System.in);
    private Coordinates createCoordinates() {
        System.out.print("Введите координату x: ");
        Long x = scanner.nextLong();
        System.out.print("Введите координату y: ");
        int y = scanner.nextInt();
        return new Coordinates(x, y);
    }

    private Furnish createFurnish() {
        System.out.print("Введите мебель (Доступные варианты: NONE, BAD, LITTLE): ");
        return Furnish.valueOf(scanner.nextCommand());
    }

    private House createHouse() {
        House house = new House();
        System.out.print("Введите имя дома (Если хотите пропустить введите пустую строку): ");
        String name = scanner.nextCommand();
        if (!name.isEmpty()) {
            house.setName(name);
        }
        System.out.print("Введите год постройки дома: ");
        house.setYear(scanner.nextInt());
        System.out.print("Введите количество лифтов: ");
        house.setNumberOfLifts(scanner.nextInt());
        return house;
    }
    private Flat createFlat() {
        Flat flat = new Flat();
        System.out.print("Введите имя квартиры: ");
        flat.setName(scanner.nextCommand());
        System.out.print("Введите координаты квартиры.\n");
        flat.setCoordinates(createCoordinates());
        System.out.print("Введите площадь квартиры: ");
        flat.setArea(scanner.nextFloat());
        System.out.print("Введите количество комнат (Если хотите пропустить введите пустую строку): ");
        String numberOfRooms = scanner.nextCommand();
        if (!numberOfRooms.isEmpty()) {
            flat.setNumberOfRooms(Long.parseLong(numberOfRooms));
        }
        System.out.print("Введите количество ванных комнат: ");
        flat.setNumberOfBathrooms(scanner.nextLong());
        System.out.print("Введите время до метро на транспорте: ");
        flat.setTimeToMetroByTransport(scanner.nextFloat());
        flat.setFurnish(createFurnish());
        flat.setHouse(createHouse());
        return new FlatFactory(FileManagerFactory.create()).create(flat).build();
    }

    /**
     * Execute.
     */
    @CommandAction
    void execute() {
        FlatManager dataManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = dataManager.getData();
        flats.add(createFlat());
    }

    @Override
    public String getCaption() {
        return "Add a new item to the collection";
    }

    @Override
    public String getMask() {
        return "add {element}";
    }
}
