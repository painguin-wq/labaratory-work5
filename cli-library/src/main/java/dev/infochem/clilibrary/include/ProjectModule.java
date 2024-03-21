package dev.infochem.clilibrary.include;

import com.google.inject.AbstractModule;
import dev.infochem.clilibrary.CommandContainer;
import dev.infochem.clilibrary.Parser;
import dev.infochem.clilibrary.Project;
import dev.infochem.clilibrary.include.CommandContainerImpl;
import dev.infochem.clilibrary.include.ParserImpl;
import dev.infochem.clilibrary.include.ProjectImpl;

public class ProjectModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CommandContainer.class).to(CommandContainerImpl.class);
        bind(Parser.class).to(ParserImpl.class);
        bind(Project.class).to(ProjectImpl.class);
    }
}
