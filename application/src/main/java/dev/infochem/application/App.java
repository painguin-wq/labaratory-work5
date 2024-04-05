package dev.infochem.application;

import dev.infochem.application.command.*;
import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.clilibrary.Application;
import dev.infochem.clilibrary.Project;

import java.util.Scanner;

/**
 * The type App.
 */
public class App extends Application {
    @Override
    public void apply(Project project) {
        project.getCommands().register("show", ShowCommand.class);
        project.getCommands().register("add", AddCommand.class);
        project.getCommands().register("help", HelpCommand.class);
        project.getCommands().register("update", UpdateCommand.class);
        project.getCommands().register("execute_script", ExecuteScriptCommand.class);
        project.getCommands().register("remove_first", RemoveFirstCommand.class);
        project.getCommands().register("remove_head", RemoveHeadCommand.class);
        project.getCommands().register("remove_by_id", RemoveByIdCommand.class);
        project.getCommands().register("save", SaveCommand.class);
        project.getCommands().register("print_ascending", PrintAscendingCommand.class);
        project.getCommands().register("filter_starts_with_name", FilterStartsWithNameCommand.class);
        project.getCommands().register("clear", ClearCommand.class);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String pathToDatabase = args[0];
            FileManagerFactory.initialize(pathToDatabase);
            System.out.print("Enter commands: ");
            String[] cmdArgs = new Scanner(System.in).nextLine().split(" ");
            launch(cmdArgs);
        } else {
            System.err.println("To work with the library, you need to specify the database path with the first parameter");
        }
    }
}
