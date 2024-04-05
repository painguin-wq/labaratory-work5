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

public class UpdateCommand extends DefaultCommand {
    @CommandAction
    void update(Integer id) throws NoSuchFieldException, IllegalAccessException {
        FlatManager dataManager = FileManagerFactory.create();
        Optional<Flat> flatOpt = dataManager.getData().stream().filter(flat1 -> flat1.getId() == id).findFirst();
        if (flatOpt.isPresent()) {
            Flat flat = flatOpt.get();
            System.out.println("Enter name of the that you want to change.\nList of available elements");
            System.out.println("\t- name (String)");
            System.out.println("\t- coordinates (Coordinates)");
            System.out.println("\t- area (Float)");
            System.out.println("\t- numberOfRooms (Long)");
            System.out.println("\t- numberOfBathrooms (Long)");
            System.out.println("\t- timeToMetroByTransport (Float)");
            System.out.println("\t- furnish (Furnish)");
            System.out.println("\t- house (House)");

            CommandsScanner scanner = new CommandsScanner(System.in);
            Field field = Flat.class.getDeclaredField(scanner.nextCommand());
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
            System.err.printf("Cannot find element with id = %d%n", id);
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
