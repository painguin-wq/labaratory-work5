package dev.infochem.clilibrary;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.infochem.clilibrary.include.ProjectModule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public abstract class Application {
    public abstract void apply(Project project);

    private static Project project;
    private static final Method setProjectMethod;

    static {
        try {
            setProjectMethod = DefaultCommand.class.getDeclaredMethod("setProject", Project.class);
            setProjectMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void launch(String... args) {
        Injector injector = Guice.createInjector(new ProjectModule());
        project = injector.getInstance(Project.class);
        Application application = getInstance();
        application.apply(project);
        project.getCommands().forEach(Application::injectProject);
        executeActions(project, args);
    }

    private static void injectProject(Command command) {
        try {
            setProjectMethod.invoke(command, project);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private static Application getInstance() {
        String callingClassName = determineApplicationClass();
        try {
            Class<?> clazz = Class.forName(callingClassName, false,
                    Thread.currentThread().getContextClassLoader());
            if (Application.class.isAssignableFrom(clazz)) {
                Class<? extends Application> applicationClass = (Class<? extends Application>) clazz;
                Constructor<? extends Application> applicationClassConstructor = applicationClass.getConstructor();
                return applicationClassConstructor.newInstance();
            } else {
                throw new RuntimeException(clazz
                        + " is not a subclass of dev.infochem.clilibrary.Project");
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
            }
    }
    private static String determineApplicationClass() {
        StackTraceElement[] cause = Thread.currentThread().getStackTrace();

        boolean foundThisMethod = false;
        String callingClassName = null;
        for (StackTraceElement se : cause) {
            String className = se.getClassName();
            String methodName = se.getMethodName();
            if (foundThisMethod) {
                callingClassName = className;
                break;
            } else if (Application.class.getName().equals(className)
                    && "launch".equals(methodName)) {
                foundThisMethod = true;
            }
        }
        if (callingClassName == null) {
            throw new RuntimeException("Unable to determine dev.infochem.clilibrary.Application class");
        }
        return callingClassName;
    }

    private static void executeActions(Project project, String[] args) {
        Action[] actions = project.getParser().parse(args);
        for (Action action : actions) {
            action.execute();
        }
    }
}
