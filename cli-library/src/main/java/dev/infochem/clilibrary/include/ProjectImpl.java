package dev.infochem.clilibrary.include;

import com.google.inject.Inject;
import dev.infochem.clilibrary.CommandContainer;
import dev.infochem.clilibrary.Parser;
import dev.infochem.clilibrary.Project;

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
}
