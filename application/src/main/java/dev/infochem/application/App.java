package dev.infochem.application;

import dev.infochem.application.command.*;
import dev.infochem.clilibrary.Application;
import dev.infochem.clilibrary.Project;

import java.util.Scanner;

public class App extends Application {
    @Override
    public void apply(Project project) {
        project.getCommands().register("show", ShowCommand.class);
        project.getCommands().register("add", AddCommand.class);
        project.getCommands().register("help", HelpCommand.class);
        project.getCommands().register("execute_script", ExecuteScriptCommand.class);
        project.getCommands().register("remove_first", RemoveFirstCommand.class);
        project.getCommands().register("remove_head", RemoveHeadCommand.class);
        project.getCommands().register("remove_by_id", RemoveByIdCommand.class);
    }

    public static void main(String[] args) {

        System.out.print("Enter commands: ");
        String[] cmdArgs = new Scanner(System.in).nextLine().split(" ");
        launch(cmdArgs);
    }
}
