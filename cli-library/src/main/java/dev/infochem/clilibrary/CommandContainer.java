package dev.infochem.clilibrary;

import java.util.Iterator;
import java.util.function.Consumer;

public interface CommandContainer {
    void register(String command, Class<? extends Command> commandClass);

    Command get(String command);

    String getNameByType(Class<?> commandType);

    boolean contains(String name);

    void forEach(Consumer<? super Command> action);
}
