package dev.infochem.application.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class FileManagerFactory {
    private static final FlatManager database = createInstance();
    private static FlatManager createInstance() {
        try {
            Constructor<FlatManager> databaseConstructor = FlatManager.class.getDeclaredConstructor();
            databaseConstructor.setAccessible(true);
            return databaseConstructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static FlatManager create() {
        return database;
    }
}
