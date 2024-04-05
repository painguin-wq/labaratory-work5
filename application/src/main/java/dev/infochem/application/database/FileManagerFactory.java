package dev.infochem.application.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class FileManagerFactory {
    private static FlatManager database = null;
    private static FlatManager createInstance(String pathToData) {
        try {
            Constructor<FlatManager> databaseConstructor = FlatManager.class.getDeclaredConstructor(String.class);
            databaseConstructor.setAccessible(true);
            return databaseConstructor.newInstance(pathToData);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialize(String pathToData) {
        if (database == null) {
            database = createInstance(pathToData);
        }
    }

    public static FlatManager create() {
        if (database != null) {
            return database;
        } else {
            throw new UnsupportedOperationException("To work with FileManagerFactory, you first need to specify the path to the database through the initialize(String) method");
        }
    }
}
