package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.Coordinates;
import dev.infochem.application.model.Flat;
import dev.infochem.application.model.Furnish;
import dev.infochem.application.model.House;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.lang.reflect.Field;
import java.util.Optional;

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
        Optional<Flat> flatOpt = dataManager.getData().stream().filter(flat1 -> flat1.getId() == id).findFirst();
        if (flatOpt.isPresent()) {
            Flat flat = flatOpt.get();
            System.out.println("Введите название элемента, который вы хотите изменить.\n");
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
            Field field = null;
            while (field == null) {
                try {
                    field = Flat.class.getDeclaredField(scanner.nextCommand());
                } catch (NoSuchFieldException e) {
                    System.out.println("Данный элемент не был найден");
                }
            }
            System.out.printf("Enter new value of field \"%s\": ", field.getName());
            field.setAccessible(true);
            String newValue = scanner.nextCommand();
            Class<?> fieldType = field.getType();
            if (fieldType == String.class) {
                field.set(flat, newValue);
            } else if (fieldType == Long.class || fieldType == long.class) {
                setLong(newValue, field, flat);
            } else if (fieldType == Float.class || fieldType == float.class) {
                setFloat(newValue, field, flat);
            } else if (fieldType == Coordinates.class) {
                Coordinates coordinates = flat.getCoordinates();
                System.out.println("Enter the x coordinate: ");
                coordinates.setX(Long.parseLong(scanner.nextCommand()));
                System.out.println("Enter the y coordinate: ");
                coordinates.setY(Integer.parseInt(scanner.nextCommand()));
            } else if (fieldType == House.class) {
                House house = flat.getHouse();
                System.out.println("Enter the name of the house: ");
                house.setName(scanner.nextCommand());
                System.out.println("Enter the year of the house: ");
                house.setYear(Integer.parseInt(scanner.nextCommand()));
                System.out.println("Enter the number of lifts in the house: ");
                house.setNumberOfLifts(Integer.parseInt(scanner.nextCommand()));
            } else if (fieldType == Furnish.class) {
                System.out.println("Enter furnish (Available variants: NONE, BAD, LITTLE): ");
                flat.setFurnish(Furnish.valueOf(scanner.nextCommand()));
            }

        } else {
            System.out.printf("Не удается найти элемент с id = %d%n", id);
        }

    }

    private void setLong(String value, Field field, Object object) throws IllegalAccessException {
        Long longValue = Long.valueOf(value);
        field.set(object, longValue);
    }

    private void setFloat(String value, Field field, Object object) throws IllegalAccessException {
        Float floatValue = Float.valueOf(value);
        field.set(object, floatValue);
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
