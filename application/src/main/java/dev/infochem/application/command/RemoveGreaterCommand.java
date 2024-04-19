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
import java.util.ArrayDeque;

public class RemoveGreaterCommand extends DefaultCommand {
    @CommandAction
    void remove() {
        FlatManager flatManager = FileManagerFactory.create();
        ArrayDeque<Flat> flats = flatManager.getData();
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
        Field field = null;
        while (field == null) {
            try {
                field = Flat.class.getDeclaredField(scanner.nextCommand());
            } catch (NoSuchFieldException e) {
                System.out.println("Данный элемент не был найден");
                System.out.println(listOfElements);
            }
        }
        field.setAccessible(true);
        Class<?> fieldType = field.getType();

        if (field.getName().equals("name")) {
            String nameInput = inputRequest(() -> {
                String name = scanner.nextCommand();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Имя поля должно быть не пустым");
                }
                return name;
            }, "Введите имя квартиры, все квартиры с именем длиннее введенного будут удалены: ", "Введено невалидное имя, попробуйте еще раз.");
            flats.removeIf(flat -> flat.getName().length() > nameInput.length());
        } else if (field.getName().equals("numberOfRooms")) {
            Long numberOfRoomsInput = (inputRequest(() -> {
                String numberOfRooms = scanner.nextCommand();
                if (!numberOfRooms.isEmpty()) {
                    Long rooms = Long.parseLong(numberOfRooms);
                    if (rooms < 0) {
                        throw new IllegalArgumentException("Количество комнат должно быть больше 0");
                    }
                    return rooms;
                } return null;
            }, "Введите количество комнат, все квартиры с количество больше введенного будут удалены: ", "Количество комнат должно быть числом."));
            flats.removeIf(flat -> flat.getNumberOfRooms() > numberOfRoomsInput);

        } else if (field.getName().equals("numberOfBathrooms")) {
            Long numberOfBathroomsInput = inputRequest(() -> {
                Long numberOfBathrooms = scanner.nextLong();
                if (numberOfBathrooms < 0) {
                    throw new IllegalArgumentException("Количество ванных комнат должно быть больше 0");
                }
                return numberOfBathrooms;
            },"Введите количество ванных комнат, все квартиры с количество больше введенного будут удалены: ", "Количество ванных комнат должно быть числом");
            flats.removeIf(flat -> flat.getNumberOfBathrooms() > numberOfBathroomsInput);
        } else if (field.getName().equals("timeToMetroByTransport")) {
            Float timeToMetroByTransportInput = inputRequest(() ->  {
                float timeToMetroByTransport = scanner.nextFloat();
                if (timeToMetroByTransport < 0) {
                    throw new IllegalArgumentException("Значение времени до метро на транспорте должно быть больше 0");
                }
                return timeToMetroByTransport;
            }, "Введите время до метро на транспорте, все квартиры с временем больше введенного будут удалены: ", "Время до метро на транспорте должно быть числом.");
            flats.removeIf(flat -> flat.getTimeToMetroByTransport() > timeToMetroByTransportInput);
        } else if(field.getName().equals("area")) {
            Float areaInput = inputRequest(() -> {
                Float area = scanner.nextFloat();
                if (area < 0) {
                    throw new IllegalArgumentException("Площадь поля должна быть больше 0");
                }
                return area;
            }, "Введите площадь квартиры, все квартиры с площадью больше введенного будут удалены: ", "Площадь квартиры должна быть числом");
            flats.removeIf(flat -> flat.getArea() > areaInput);
        } else if (fieldType == Coordinates.class) {
            final String coordinatesInputError = "Координата должна быть числом!";
            Coordinates coordinates = new Coordinates(0L, 0);
            coordinates.setX(inputRequest(() -> {
                Long xCoordinate = scanner.nextLong();
                if (xCoordinate == null || xCoordinate < -336) {
                    throw new IllegalArgumentException("Поле x должно быть не пустым и превышать -336");
                }
                return xCoordinate;
            }, "Введите координату x: ", coordinatesInputError));
            coordinates.setY(inputRequest(scanner::nextInt, "Введите координату y: ", coordinatesInputError));
            flats.removeIf(flat -> flat.getCoordinates().compareTo(coordinates) < 0);
        } else if (fieldType == House.class) {
            House house = new House();
            house.setName(inputRequest(() -> {
                String name = scanner.nextCommand();
                if (!name.isEmpty()) {
                    return name;
                } return null;
            }, "Введите новое имя дома (Если хотите пропустить введите пустую строку): ", "Возникла ошибка, попробуйте еще раз"));

            house.setYear(inputRequest(() -> {
                Integer year = scanner.nextInt();
                if (year < 0) {
                    throw new IllegalArgumentException("Год постройки должен быть больше нуля");
                }
                return year;
            }, "Введите новый год постройки дома: ", "Год постройки должен быть числом"));

            house.setNumberOfLifts(inputRequest(() -> {
                Integer numberOfLifts = scanner.nextInt();
                if (numberOfLifts < 0) {
                    throw new IllegalArgumentException("Количество лифтов должно быть больше нуля");
                } return numberOfLifts;
            }, "Введите новое количество лифтов: ", "Количество лифтов должно быть числом."));
            flats.removeIf(flat -> flat.getHouse().compareTo(house) < 0);
        } else if (fieldType == Furnish.class) {
            Furnish furnishInput = inputRequest(() -> {
                        String furnish = scanner.nextCommand();
                        try {
                            return Furnish.valueOf(furnish);
                        } catch (IllegalArgumentException ex) {
                            throw new UnsupportedOperationException("Доступны только варианты: NONE, BAD, LITTLE!");
                        }
                    }, "Введите новую мебель (Доступные варианты: NONE, BAD, LITTLE): ", "Доступны только варианты: NONE, BAD, LITTLE!"
            );
            flats.removeIf(flat -> flat.getFurnish().compareTo(furnishInput) < 0);
        }
        flatManager.resolveIds();
    }
    @Override
    public String getCaption() {
        return "Output the greater element of the collection and delete it";
    }

    @Override
    public String getMask() {
        return "remove_greater <element>";
    }
}
