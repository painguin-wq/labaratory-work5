package dev.infochem.clilibrary.include;

import dev.infochem.clilibrary.Command;
import dev.infochem.clilibrary.CommandContainer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandContainerImpl implements CommandContainer {
    private final Map<String, Command> container = new HashMap<>();

    CommandContainerImpl() {}

    @Override
    public void register(String command, Class<? extends Command> commandClass) {
        if (!contains(command)) {
            try {
                Constructor<? extends Command> commandConstructor = commandClass.getConstructor();
                Command commandInstance = commandConstructor.newInstance();
                container.put(command, commandInstance);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new IllegalArgumentException("Error creating an entity of the %s class. ".formatted(commandClass.getSimpleName())
                + "The class where the command is implemented must have a constructor without parameters.", e);
            }
        } else {
            throw new UnsupportedOperationException("dev.infochem.clilibrary.Command \"%s\" is already reserved and cannot be added to the project.".formatted(command));
        }
    }

    @Override
    public Command get(String command) {
        if (contains(command)) {
            return container.get(command);
        } else {
            throw new IllegalArgumentException("dev.infochem.clilibrary.Command %s has not been registered".formatted(command));
        }
    }

    @Override
    public String getNameByType(Class<?> commandType) {
        for (String key : container.keySet()) {
            if (commandType.isInstance(container.get(key))) {
                return key;
            }
        }
        throw new IllegalArgumentException("Cannot find command with %s type".formatted(commandType));
    }

    @Override
    public boolean contains(String command) {
        return container.containsKey(command);
    }

    @Override
    public void forEach(Consumer<? super Command> action) {
        container.forEach((name, command) -> action.accept(command));
    }

    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }
}
