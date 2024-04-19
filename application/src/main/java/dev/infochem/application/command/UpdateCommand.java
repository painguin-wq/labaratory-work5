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
                    System.out.println(listOfElements);
                }
            }
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            if (field.getName().equals("name")) {
                flat.setName(inputRequest(() -> {
                    String name = scanner.nextCommand();
                    if (name == null || name.isEmpty()) {
                        throw new IllegalArgumentException("Имя поля должно быть не пустым");
                    }
                    return name;
                }, "Введите новое имя квартиры: ", "Введено невалидное имя, попробуйте еще раз."));
            } else if (field.getName().equals("numberOfRooms")) {
                flat.setNumberOfRooms(inputRequest(() -> {
                    String numberOfRooms = scanner.nextCommand();
                    if (!numberOfRooms.isEmpty()) {
                        Long rooms = Long.parseLong(numberOfRooms);
                        if (rooms < 0) {
                            throw new IllegalArgumentException("Количество комнат должно быть больше 0");
                        }
                        return rooms;
                    } return null;
                }, "Введите новое количество комнат (Если хотите пропустить введите пустую строку): ", "Количество комнат должно быть числом."));
            } else if (field.getName().equals("numberOfBathrooms")) {
                flat.setNumberOfBathrooms(inputRequest(() -> {
                    Long numberOfBathrooms = scanner.nextLong();
                    if (numberOfBathrooms < 0) {
                        throw new IllegalArgumentException("Количество ванных комнат должно быть больше 0");
                    }
                    return numberOfBathrooms;
                },"Введите новое количество ванных комнат: ", "Количество ванных комнат должно быть числом"));

            } else if (field.getName().equals("timeToMetroByTransport")) {
                flat.setTimeToMetroByTransport(inputRequest(() ->  {
                    float timeToMetroByTransport = scanner.nextFloat();
                    if (timeToMetroByTransport < 0) {
                        throw new IllegalArgumentException("Значение времени до метро на транспорте должно быть больше 0");
                    }
                    return timeToMetroByTransport;
                }, "Введите новое время до метро на транспорте: ", "Время до метро на транспорте должно быть числом."));

            } else if(field.getName().equals("area")) {
                flat.setArea(inputRequest(() -> {
                    Float area = scanner.nextFloat();
                    if (area < 0) {
                        throw new IllegalArgumentException("Площадь поля должна быть больше 0");
                    }
                    return area;
                }, "Введите новую площадь квартиры: ", "Площадь квартиры должна быть числом"));
            } else if (fieldType == Coordinates.class) {
                final String coordinatesInputError = "Координата должна быть числом!";
                Coordinates coordinates = flat.getCoordinates();
                System.out.println("Введите новою координату x: ");
                coordinates.setX(inputRequest(() -> {
                    Long xCoordinate = scanner.nextLong();
                    if (xCoordinate == null || xCoordinate < -336) {
                        throw new IllegalArgumentException("Поле x должно быть не пустым и превышать -336");
                    }
                    return xCoordinate;
                }, "Введите координату x: ", coordinatesInputError));
                coordinates.setY(inputRequest(scanner::nextInt, "Введите координату y: ", coordinatesInputError));
            } else if (fieldType == House.class) {
                House house = flat.getHouse();
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
            } else if (fieldType == Furnish.class) {
                flat.setFurnish(inputRequest(() -> {
                            String furnish = scanner.nextCommand();
                            try {
                                return Furnish.valueOf(furnish);
                            } catch (IllegalArgumentException ex) {
                                throw new UnsupportedOperationException("Доступны только варианты: NONE, BAD, LITTLE!");
                            }
                        }, "Введите новую мебель (Доступные варианты: NONE, BAD, LITTLE): ", "Доступны только варианты: NONE, BAD, LITTLE!"
                ));
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
