package dev.infochem.application;

import dev.infochem.application.command.AddCommand;
import dev.infochem.application.command.HelpCommand;
import dev.infochem.application.command.ShowCommand;
import dev.infochem.clilibrary.Application;
import dev.infochem.clilibrary.Project;

public class App extends Application {
    @Override
    public void apply(Project project) {
        project.getCommands().register("show", ShowCommand.class);
        project.getCommands().register("add", AddCommand.class);
        project.getCommands().register("help", HelpCommand.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
