package dev.infochem.clilibrary.include;

import com.google.inject.Inject;
import dev.infochem.clilibrary.*;

import java.util.ArrayList;
import java.util.Objects;

public class ParserImpl implements Parser {
    private final CommandContainer commandContainer;
    @Inject
    ParserImpl(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }
    @Override
    public CommandContainer getCommands() {
        return commandContainer;
    }

    @Override
    public Action[] parse(String[] args) {
        ArrayList<Action> actions = new ArrayList<>();
        ArrayList<Object> buffer = new ArrayList<>();
        Command commandBuffer = null;
        for (String arg : args) {
            arg = arg.toLowerCase();
            if (getCommands().contains(arg)) {
                Command command = getCommands().get(arg);
                if (commandBuffer == null) {
                    commandBuffer = command;
                } else {
                    actions.add(commandBuffer.getAction(buffer.toArray()));
                    buffer.clear();
                    commandBuffer = command;
                }
            } else {
                if (commandBuffer == null) {
                    throw new UnknownCommandException("Unknown command \"%s\"".formatted(arg));
                }
                typedAdd(arg, buffer);
            }
        }
        if (commandBuffer != null) {
            actions.add(commandBuffer.getAction(buffer.toArray()));
        }
        return actions.toArray(new Action[]{});
    }

    private void typedAdd(String arg, ArrayList<Object> objects) {
        try {
            objects.add(Integer.parseInt(arg));
            return;
        } catch (NumberFormatException ignored) {}
        try {
            objects.add(Double.parseDouble(arg));
        } catch (NumberFormatException e) {
            objects.add(arg);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ParserImpl parser = (ParserImpl) object;
        return Objects.equals(commandContainer, parser.commandContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandContainer);
    }

    @Override
    public String toString() {
        return "ParserImpl{" +
                "commandContainer=" + commandContainer +
                '}';
    }
}
