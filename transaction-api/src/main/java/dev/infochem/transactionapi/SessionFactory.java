package dev.infochem.transactionapi;

import dev.infochem.transactionapi.include.SessionImpl;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class SessionFactory {
    private static final Map<String, Session> sessionMap = new HashMap<>();

    private static Session createInstance(String path) {
        try {
            Constructor<SessionImpl> sessionConstructor = SessionImpl.class.getDeclaredConstructor(String.class);
            sessionConstructor.setAccessible(true);
            return sessionConstructor.newInstance(path);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static Session create(File file) {
        String absolutePath = file.getAbsolutePath();
        if (sessionMap.containsKey(absolutePath)) {
            Session session = sessionMap.get(absolutePath);
            if (session.isClose()) {
                sessionMap.remove(absolutePath);
            } else {
                return session;
            }
        }
        Session session = createInstance(absolutePath);
        return sessionMap.put(absolutePath, session);
    }

    public static Session get(File file) {
        String absolutePath = file.getAbsolutePath();
        if (!sessionMap.containsKey(absolutePath)) {
            throw new IllegalArgumentException("There is no session of %s file".formatted(absolutePath));
        }
        Session session = sessionMap.get(absolutePath);
        if (session.isClose()) {
            throw new UnsupportedOperationException("Session for %s file is closed".formatted(absolutePath));
        }
        return session;
    }

    public static void close(Session session) {
        if (sessionMap.containsKey(session.getFile().getAbsolutePath())){
            sessionMap.remove(session.getFile().getAbsolutePath()).close();
        }
    }
}
