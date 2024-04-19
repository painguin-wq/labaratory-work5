package dev.infochem.clilibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class DefaultCommand implements Command {
    private Project project;

    public <T> T inputRequest(Supplier<T> inputFunc, String requestsMessage, String castMistakeMessage) {
        T data;
        System.out.print(requestsMessage);
        while (true) {
            try {
                data = inputFunc.get();
                break;
            } catch (ClassCastException | NumberFormatException exception) {
                System.out.println(castMistakeMessage);
                System.out.print(requestsMessage);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.print(requestsMessage);
            }
        }
        return data;
    }

    @Override
    public Project getProject() {
        return project;
    }

    private void setProject(Project project) {
        if (this.project == null) {
            this.project = project;
        }
    }
    @Override
    public Action getAction(Object... parameters) {
        Class<?>[] parameterTypes = Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new);
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CommandAction.class)
                    && method.getParameterTypes().length == parameterTypes.length
                    && compareTypes(parameterTypes, method.getParameterTypes())) {
                return () -> {
                    try {
                        method.setAccessible(true);
                        method.invoke(this, parameters);

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                };
            }
        }
        String command = getProject().getCommands().getNameByType(getClass());
        System.out.printf("Не удается найти действие с этими параметрами в команде %s%n", command);
        System.out.printf("Вы можете использовать команду %s используя маску: %s%n", command, getProject().getCommands().get(command).getMask());
        return null;
    }

    private boolean compareTypes(Class<?>[] parsedArray, Class<?>[] requirementsArray) {
        if (parsedArray.length == requirementsArray.length) {
            for (int i =0; i < parsedArray.length; i++) {
                if (!parsedArray[i].isAssignableFrom(requirementsArray[i])) {
                    if (!requirementsArray[i].isAssignableFrom(String.class)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private Object[] castArrays(Class<?>[] requirementsTypesArray, Object[] parameters) {
        List<Object> resultList = new ArrayList<>();
        if (requirementsTypesArray.length == parameters.length) {
            for (int i = 0; i < requirementsTypesArray.length; i++) {
                if (parameters[i].getClass() != requirementsTypesArray[i]) {
                    if (requirementsTypesArray[i] == String.class) {
                        resultList.add(parameters[i].toString());
                    } else {
                        resultList.add(requirementsTypesArray[i].cast(parameters[i]));
                    }
                }
            }
            return resultList.toArray();
        }
        throw new UnsupportedOperationException("Error during casting parsing parameters to required types form method");
    }

    @Override
    public String toString() {
        return "%s command".formatted(getProject().getCommands().getNameByType(getClass()));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        return Objects.equals(getProject().getCommands().getNameByType(getClass()), getProject().getCommands().getNameByType(object.getClass()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProject().getCommands().getNameByType(getClass()));
    }
}
