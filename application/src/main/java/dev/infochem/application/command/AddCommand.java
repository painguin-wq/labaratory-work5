package dev.infochem.application.command;

import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.database.FlatManager;
import dev.infochem.application.model.*;
import dev.infochem.clilibrary.CommandAction;
import dev.infochem.clilibrary.DefaultCommand;

import java.util.ArrayDeque;

public class AddCommand extends DefaultCommand {
    private final CommandsScanner scanner = new CommandsScanner(System.in);
    private Coordinates createCoordinates() {
        final String coordinatesInputError = "Координата должна быть числом!";
        Long x = inputRequest(() -> {
            Long xCoordinate = scanner.nextLong();
            if (xCoordinate == null || xCoordinate < -336) {
                throw new IllegalArgumentException("Поле x должно быть не пустым и превышать -336");
            }
            return xCoordinate;
        }, "Введите координату x: ", coordinatesInputError);
        int y = inputRequest(scanner::nextInt, "Введите координату y: ", coordinatesInputError);
        return new Coordinates(x, y);
    }

    private Furnish createFurnish() {
        return inputRequest(() -> {
                    String furnish = scanner.nextCommand();
                    try {
                        return Furnish.valueOf(furnish);
                    } catch (IllegalArgumentException ex) {
                        throw new UnsupportedOperationException("Доступны только варианты: NONE, BAD, LITTLE!");
                    }
                }, "Введите мебель (Доступные варианты: NONE, BAD, LITTLE): ", "Доступны только варианты: NONE, BAD, LITTLE!"
        );
    }

    private House createHouse() {
        House house = new House();
        house.setName(inputRequest(() -> {
            String name = scanner.nextCommand();
            if (!name.isEmpty()) {
                return name;
            } return null;
        }, "Введите имя дома (Если хотите пропустить введите пустую строку): ", "Возникла ошибка, попробуйте еще раз"));

        house.setYear(inputRequest(() -> {
            Integer year = scanner.nextInt();
            if (year < 0) {
                throw new IllegalArgumentException("Год постройки должен быть больше нуля");
            }
            return year;
        }, "Введите год постройки дома: ", "Год постройки должен быть числом"));

        house.setNumberOfLifts(inputRequest(() -> {
            Integer numberOfLifts = scanner.nextInt();
            if (numberOfLifts < 0) {
                throw new IllegalArgumentException("Количество лифтов должно быть больше нуля");
            } return numberOfLifts;
        }, "Введите количество лифтов: ", "Количество лифтов должно быть числом."));
        return house;
    }
    private Flat createFlat() {
        Flat flat = new Flat();
        flat.setName(inputRequest(() -> {
            String name = scanner.nextCommand();
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Имя поля должно быть не пустым");
            }
            return name;
        }, "Введите имя квартиры: ", "Введено невалидное имя, попробуйте еще раз."));

        System.out.println("Введите координаты квартиры.");
        flat.setCoordinates(createCoordinates());
        flat.setArea(inputRequest(() -> {
            Float area = scanner.nextFloat();
            if (area < 0) {
                throw new IllegalArgumentException("Площадь поля должна быть больше 0");
            }
            return area;
        }, "Введите площадь квартиры: ", "Площадь квартиры должна быть числом"));

        flat.setNumberOfRooms(inputRequest(() -> {
            String numberOfRooms = scanner.nextCommand();
            if (!numberOfRooms.isEmpty()) {
                Long rooms = Long.parseLong(numberOfRooms);
                if (rooms < 0) {
                    throw new IllegalArgumentException("Количество комнат должно быть больше 0");
                }
                return rooms;
            } return null;
        }, "Введите количество комнат (Если хотите пропустить введите пустую строку): ", "Количество комнат должно быть числом."));

        flat.setNumberOfBathrooms(inputRequest(() -> {
            Long numberOfBathrooms = scanner.nextLong();
            if (numberOfBathrooms < 0) {
                throw new IllegalArgumentException("Количество ванных комнат должно быть больше 0");
            }
            return numberOfBathrooms;
        },"Введите количество ванных комнат: ", "Количество ванных комнат должно быть числом"));

        flat.setTimeToMetroByTransport(inputRequest(() ->  {
            float timeToMetroByTransport = scanner.nextFloat();
            if (timeToMetroByTransport < 0) {
                throw new IllegalArgumentException("Значение времени до метро на транспорте должно быть больше 0");
            }
            return timeToMetroByTransport;
        }, "Введите время до метро на транспорте: ", "Время до метро на транспорте должно быть числом."));
        flat.setFurnish(createFurnish());
        flat.setHouse(createHouse());
        return new FlatFactory(FileManagerFactory.create()).create(flat).build();
    }
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
