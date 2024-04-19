package dev.infochem.clilibrary.include;

import com.google.inject.Inject;
import dev.infochem.clilibrary.CommandContainer;
import dev.infochem.clilibrary.Parser;
import dev.infochem.clilibrary.Project;

import java.util.Objects;

public class ProjectImpl implements Project {
    private final CommandContainer commandContainer;
    private final Parser parser;

    @Inject
    ProjectImpl(Parser parser) {
        this.parser = parser;
        this.commandContainer = getParser().getCommands();
    }

    @Override
    public CommandContainer getCommands() {
        return commandContainer;
    }

    @Override
    public Parser getParser() {
        return parser;
    }

    @Override
    public String toString() {
        return "ProjectImpl{" +
                "commandContainer=" + commandContainer +
                ", parser=" + parser +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProjectImpl project = (ProjectImpl) object;
        return Objects.equals(commandContainer, project.commandContainer) && Objects.equals(parser, project.parser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandContainer, parser);
    }
}
